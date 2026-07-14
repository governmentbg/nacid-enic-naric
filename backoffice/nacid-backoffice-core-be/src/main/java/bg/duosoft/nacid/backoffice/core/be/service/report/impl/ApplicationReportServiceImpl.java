package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPropertiesService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.report.ApplicationReportService;
import bg.duosoft.nacid.backoffice.core.be.service.report.ReportService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportTemplateDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacid.backoffice.rudi.client.client.appreport.AppReportClient;
import bg.duosoft.nacidbackofficeshareddata.service.ApplicationCertificatesService;
import bg.duosoft.nacidbackofficeshareddata.service.QrService;
import bg.duosoft.nacidbackofficeshareddata.validator.impl.ApplicationReportValidator;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.util.appreport.MetadataKey;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationReportServiceImpl implements ApplicationReportService {
    private final QrService qrService;
    private final ReportService reportService;
    private final ApplicationsService applicationsService;
    private final ApplicationPropertiesService applicationPropertiesService;
    private final AppReportClient appReportClient;
    private final ApplicationCertificatesService applicationCertificatesService;
    private final ApplicationReportValidator applicationReportValidator;

    private static final String CERT_NUMBER_NEW_FORMAT_DATE = "01.09.2023";

    @Override
    public List<AppReportResultDTO> generateApplicationReports(AppReportTemplateDTO appReportTemplate) {
        List<AppReportResultDTO> appReportResultList = new ArrayList<>();
        if (Objects.isNull(appReportTemplate) || CollectionUtils.isEmpty(appReportTemplate.getTemplates())) {
            throw new RuntimeException("Invalid appReportTemplate");
        }
        ApplicationDTO application = applicationsService.getApplicationById(appReportTemplate.getApplicationId());
        ApplicationType applicationType = getApplicationType(application.getId());
        validateReport(appReportTemplate, applicationType, application);
        Map<String, String> customValues = getCustomValues(appReportTemplate, applicationType, application);
        List<Pair<byte[], AppReportTemplateDTO.Template>> reports = generateReports(appReportTemplate, customValues, applicationType);
        for (Pair<byte[], AppReportTemplateDTO.Template> report : reports) {
            FileStoreEntryBaseDTO fileStoreEntry = createFileStoreEntry(appReportTemplate, report, customValues, application);
            appReportResultList.add(new AppReportResultDTO(fileStoreEntry, report.getSecond().getDefaultAttachmentVisibility()));
        }

        return appReportResultList;
    }

    private void validateReport(AppReportTemplateDTO template, ApplicationType applicationType, ApplicationDTO application) {
        List<ValidationError> errors = new ArrayList<>();
        Integer applicationId = template.getApplicationId();
        Integer documentTypeId = template.getDocumentTypeId();
        Integer attachmentId = template.getAttachmentId();
        switch (applicationType) {
            case RUDI -> {
                errors.addAll(appReportClient.getErrorsOnGenerateReport(applicationId, documentTypeId, attachmentId));
            }
            case REGPROF -> {
                errors.addAll(applicationReportValidator.validate(application, applicationReportValidator.initAttachmentBeforeValidation(documentTypeId, attachmentId)));
            }
            default -> {
            }
        }
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }
    }

    private List<Pair<byte[], AppReportTemplateDTO.Template>> generateReports(AppReportTemplateDTO appReportTemplate, Map<String, String> customValues, ApplicationType applicationType) {
        Map<String, Object> reportCustomValues = new HashMap<>();

        if (Objects.nonNull(customValues)) {
            Set<String> keySet = customValues.keySet();
            if (!CollectionUtils.isEmpty(keySet)) {
                keySet.forEach(key -> {
                    if (key.equals(MetadataKey.AR_CERTIFICATE_UUID_KEY) || key.equals(MetadataKey.RP_CERTIFICATE_UUID_KEY)) {
                        String qrKey = applicationType.equals(ApplicationType.RUDI) ? MetadataKey.AR_CERTIFICATE_QR_KEY : MetadataKey.RP_CERTIFICATE_QR_KEY;
                        reportCustomValues.put(qrKey, generateQrCode(customValues.get(key), applicationType));
                    } else {
                        reportCustomValues.put(key, customValues.get(key));
                    }
                });
            }
        }

        List<Pair<byte[], AppReportTemplateDTO.Template>> generatedReports = new ArrayList<>();
        for (AppReportTemplateDTO.Template template : appReportTemplate.getTemplates()) {
            byte[] content = reportService.generateApplicationReport(appReportTemplate.getReportType(), template.getTemplate(), appReportTemplate.getApplicationId(), appReportTemplate.getCommissionMemberId(), reportCustomValues);
            generatedReports.add(Pair.of(content, template));
        }
        return generatedReports;
    }

    private byte[] generateQrCode(String uuid, ApplicationType applicationType) {
        String qrCodeUrl = "";
        String heightKey = null;
        String widthKey = null;
        switch (applicationType) {
            case RUDI -> {
                qrCodeUrl = ApplicationProperty.RUDI_QR_CODE_URL.code();
                heightKey = ApplicationProperty.RUDI_CERTIFICATE_HEIGHT.code();
                widthKey = ApplicationProperty.RUDI_CERTIFICATE_WIDTH.code();
            }
            case REGPROF -> {
                qrCodeUrl = ApplicationProperty.NORQ_QR_CODE_URL.code();
                heightKey = ApplicationProperty.REGPROF_CERTIFICATE_HEIGHT.code();
                widthKey = ApplicationProperty.REGPROF_CERTIFICATE_WIDTH.code();
            }
            default -> {
                throw new RuntimeException("Unknown application type...");
            }
        }
        Integer height = Optional.ofNullable(applicationPropertiesService.selectById(heightKey)).map(r -> r.getValue()).map(Integer::parseInt).orElse(120);
        Integer width = Optional.ofNullable(applicationPropertiesService.selectById(widthKey)).map(r -> r.getValue()).map(Integer::parseInt).orElse(120);
        ApplicationPropertyDTO applicationPropertyDTO = applicationPropertiesService.selectById(qrCodeUrl);
        String barcodeText = applicationPropertyDTO.getValue().replace("{0}", uuid);
        return qrService.generateQRCodeImage(barcodeText, height, width);
    }


    private Pair<String, String> selectRootDirAndRelativePath(Integer applicationId) {
        ApplicationDTO application = applicationsService.getApplicationById(applicationId);
        if (Objects.isNull(application)) {
            throw new InternalServerErrorException("Cannot upload application report ! ApplicationID: " + applicationId);
        }

        return Pair.of(MinioBucketManager.selectBucketName(application), MinioBucketManager.buildAppsRelativePath(application));
    }

    private FileStoreEntryBaseDTO createFileStoreEntry(AppReportTemplateDTO templateDTO, Pair<byte[], AppReportTemplateDTO.Template> report, Map<String, String> customValues, ApplicationDTO application) {
        if (Objects.isNull(templateDTO) || report.getFirst().length < 1) {
            throw new InternalServerErrorException();
        }
        ReportType reportType = templateDTO.getReportType();
        String mimeType = reportType.getMimeType();

        Pair<String, String> pair = selectRootDirAndRelativePath(application.getId());
        String rootDirectory = pair.getFirst();
        String relativePath = pair.getSecond();


        Path path = Paths.get(report.getSecond().getTemplate());
        FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
        entry.setContent(report.getFirst());
        entry.setFileName(buildFileStoreEntityFileName(application, path, reportType));
        entry.setFileSize((long) report.getFirst().length);
        entry.setContentType(mimeType);
        entry.setRootDirectory(rootDirectory);
        entry.setRelativePath(relativePath);
        entry.setAdditionalMetadata(customValues);
        return entry;
    }

    private String buildFileStoreEntityFileName(ApplicationDTO application, Path path, ReportType reportType) {
        StringBuilder fileNameBuilder = new StringBuilder();
        if (StringUtils.hasText(application.getEntryNumber()) && Objects.nonNull(application.getEntryDate())) {
            fileNameBuilder.append(application.getEntryNumber()).append("-").append(DateUtils.formatLocalDate(application.getEntryDate())).append("-");
        }
        fileNameBuilder.append(FilenameUtils.removeExtension(path.getFileName().toString())).append(".").append(reportType.getExtension());
        return fileNameBuilder.toString();
    }


    private Map<String, String> getCustomValues(AppReportTemplateDTO template, ApplicationType applicationType, ApplicationDTO application) {
        Integer applicationId = template.getApplicationId();
        Integer documentTypeId = template.getDocumentTypeId();

        switch (applicationType) {
            case RUDI -> {
                return getRudiCustomValues(applicationId, documentTypeId);
            }
            case REGPROF -> {
                return getRegprofCustomValues(application, documentTypeId);
            }
            default -> {
                return null;
            }
        }

    }

    private ApplicationType getApplicationType(Integer applicationId) {
        Pair<String, String> appTypeAndSubtypePair = applicationsService.getAppTypeAndSubtypeById(applicationId);
        return ApplicationType.selectByCode(appTypeAndSubtypePair.getFirst());
    }

    private ApplicationSubType getApplicationSubType(Integer applicationId) {
        Pair<String, String> appTypeAndSubtypePair = applicationsService.getAppTypeAndSubtypeById(applicationId);
        return ApplicationSubType.selectByTypeAndSubType(appTypeAndSubtypePair.getFirst(), appTypeAndSubtypePair.getSecond());
    }

    private Map<String, String> getRegprofCustomValues(ApplicationDTO application, Integer documentTypeId) {
        Map<String, String> customValues = new HashMap<>();
        ConditionalDocTypes conditionalDocTypes = ConditionalDocTypes.selectByCode(documentTypeId);

        if (Objects.nonNull(conditionalDocTypes)) {
            if (conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE)) {
                customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, application.getEntryNumber().concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
            }
            if (conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE_DUPLICATE) || conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE_OBVIOUS_MISTAKE)) {
                fillCertificateNumberBasedOnLastPublished(customValues, application.getId());
            }
            customValues.put(MetadataKey.RP_CERTIFICATE_UUID_KEY, UUID.randomUUID().toString());
        }
        return customValues;
    }

    private Map<String, String> getRudiCustomValues(Integer applicationId, Integer documentTypeId) {
        Map<String, String> customValues = new HashMap<>();
        ConditionalDocTypes conditionalDocTypes = ConditionalDocTypes.selectByCode(documentTypeId);

        if (Objects.nonNull(conditionalDocTypes)) {

            if (conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE)) {
                String certificateNumber = appReportClient.getCertificateNumber(applicationId);
                if (Objects.isNull(certificateNumber)) {
                    throw new InternalServerErrorException("Certificate number is empty!");
                }
                customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, certificateNumber);
            }
            if (conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE_DUPLICATE) || conditionalDocTypes.equals(ConditionalDocTypes.CERTIFICATE_OBVIOUS_MISTAKE)) {
                fillCertificateNumberBasedOnLastPublished(customValues, applicationId);
            }
            customValues.put(MetadataKey.AR_CERTIFICATE_UUID_KEY, UUID.randomUUID().toString());
        }


        return customValues;
    }

    private void fillCertificateNumberBasedOnLastPublished(Map<String, String> customValues, Integer applicationId) {
        ApplicationCertificatesDTO publishedCertificate = applicationCertificatesService.selectByStatusAndApplicationId(applicationId, ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code()).get(0);
        if (Objects.isNull(publishedCertificate) || !StringUtils.hasText(publishedCertificate.getCertificateNumber())) {
            throw new InternalServerErrorException("Published certificate number does not exist!");
        }
        ApplicationSubType applicationSubType = getApplicationSubType(applicationId);
        switch (applicationSubType) {
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                fillUdirecCertificateNumber(customValues, publishedCertificate, isCertNumberWithOldFormat(applicationId, publishedCertificate));
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                fillDocrecCertificateNumber(customValues, publishedCertificate);
            }
            default -> {
                fillCommonCertificateNumber(customValues, publishedCertificate);
            }
        }

    }

    private void fillCommonCertificateNumber(Map<String, String> customValues, ApplicationCertificatesDTO publishedCertificate) {
        String[] certNumberArray = publishedCertificate.getCertificateNumber().split("/");
        String[] certNumberMainPartArray = certNumberArray[0].split("-");
        if (certNumberMainPartArray.length > 3) {
            int lastNumber = Integer.parseInt(certNumberMainPartArray[3]);
            String applicationEntryNumber = certNumberArray[0].replace("-".concat(String.valueOf(lastNumber)), "");
            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, applicationEntryNumber.concat("-").concat(String.valueOf(lastNumber + 1)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
        } else {
            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, certNumberArray[0].concat("-").concat(String.valueOf(1)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
        }
    }

    private void fillDocrecCertificateNumber(Map<String, String> customValues, ApplicationCertificatesDTO publishedCertificate) {
        fillCommonCertificateNumber(customValues, publishedCertificate);
    }


    private boolean isCertNumberWithOldFormat(Integer applicationId, ApplicationCertificatesDTO publishedCertificate) {
        List<ApplicationCertificatesDTO> destroyedCertificates = applicationCertificatesService.selectByStatusAndApplicationId(applicationId, ReferenceDataCode.CERTIFICATE_STATUS_DESTROYED.code());
        String[] certNumberArray = null;
        if (!CollectionUtils.isEmpty(destroyedCertificates)) {
            destroyedCertificates.sort(Comparator.comparing(ApplicationCertificatesDTO::getId));
            ApplicationCertificatesDTO mainCertificate = destroyedCertificates.get(0);
            certNumberArray = mainCertificate.getCertificateNumber().split("/");
        } else {
            certNumberArray = publishedCertificate.getCertificateNumber().split("/");
        }
        return DateUtils.convertToLocalDate(certNumberArray[1]).isBefore(DateUtils.convertToLocalDate(CERT_NUMBER_NEW_FORMAT_DATE));
    }

    private void fillUdirecCertificateNumber(Map<String, String> customValues, ApplicationCertificatesDTO publishedCertificate, boolean isOldFormat) {
        String[] certNumberArray = publishedCertificate.getCertificateNumber().split("/");

        String[] certNumberMainPartArray = certNumberArray[0].split("-");
        String newCertificateNumberMainPart = certNumberMainPartArray[0].concat("-").concat(certNumberMainPartArray[1]).concat("-");
        if (!isOldFormat) {
            newCertificateNumberMainPart = newCertificateNumberMainPart.concat(certNumberMainPartArray[2]).concat("-");
        }
        if (certNumberMainPartArray.length > 3 && !isOldFormat) {
            int lastNumber = Integer.parseInt(certNumberMainPartArray[3]);
            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, newCertificateNumberMainPart.concat(String.valueOf(lastNumber + 1)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
        } else if (certNumberMainPartArray.length > 2 && isOldFormat) {
            int lastNumber = Integer.parseInt(certNumberMainPartArray[2]);
            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, newCertificateNumberMainPart.concat(String.valueOf(lastNumber + 1)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
        } else {
            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, newCertificateNumberMainPart.concat(String.valueOf(1)).concat("/").concat(DateUtils.formatLocalDate(LocalDate.now())));
        }
    }

}
