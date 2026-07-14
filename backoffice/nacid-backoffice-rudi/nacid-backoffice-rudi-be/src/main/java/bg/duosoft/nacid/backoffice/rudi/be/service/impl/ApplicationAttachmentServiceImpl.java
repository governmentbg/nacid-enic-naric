package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.client.client.common.report.ReportClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype.DocumentTypeClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportFilter;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.UpdateAttachmentsResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarGlobalReportDTO;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacid.backoffice.rudi.be.service.AppReportCustomValuesService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationAttachmentService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiApplicationReportValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.GlobalReportValidator;
import bg.duosoft.nacidbackofficeshareddata.service.ApplicationCertificatesService;
import bg.duosoft.nacidbackofficeshareddata.service.impl.BaseApplicationAttachmentServiceImpl;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.util.appreport.MetadataKey;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationAttachmentServiceImpl extends BaseApplicationAttachmentServiceImpl implements ApplicationAttachmentService {
    private final RudiApplicationService rudiApplicationService;
    private final RudiApplicationReportValidator applicationReportValidator;
    private final ApplicationCertificatesService applicationCertificatesService;
    private final ReportClient reportClient;
    private final GlobalReportValidator globalReportValidator;
    private final DocumentTypeClient documentTypeClient;
    private final AppReportCustomValuesService appReportCustomValuesService;
    private final CommissionApplicationService commissionApplicationService;

    @Override
    public AttachedDocDTO saveAttachment(Integer applicationId, AttachedDocDTO attachment) {

        RudiApplicationDTO application = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }

        List<ValidationError> errors = applicationReportValidator.validate(application.getApplication(), attachment);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        UpdateAttachmentsResultDTO updateAttachmentsResult = attachmentUpdates(application.getApplication(), attachment);
        RudiApplicationDTO savedApplication = rudiApplicationService.save(application, ValidationScope.APP_ATTACHMENTS);
        addNewCertificateOnCreate(savedApplication.getApplication(), updateAttachmentsResult);

        AttachedDocDTO savedAttachedDoc;
        if (Objects.nonNull(attachment.getId())) {
            savedAttachedDoc = savedApplication.getApplication().getAttachments().stream().filter(r -> r.getId().equals(attachment.getId())).findFirst().orElse(null);
        } else {
            savedAttachedDoc = savedApplication.getApplication().getAttachments().stream().max(Comparator.comparing(AttachedDocDTO::getId)).orElse(null);
        }

        return savedAttachedDoc;
    }

    @Override
    @LogObjectChange(id = "#id", before = "#root.target.selectAttachment(#id)", operation = "'delete'")
    public void delete(Integer id) {
        deleteAttachmentInternal(id);
    }

    @Override
    public Map<String, FileStoreEntryBaseDTO> generateGlobalReport(CommissionCalendarGlobalReportDTO globalReportDTO) {
        if (CollectionUtils.isEmpty(globalReportDTO.getApplicationIds())) {
            throw new InternalServerErrorException("Application ids list not presented!");
        }
        if (Objects.isNull(globalReportDTO.getDocumentType())) {
            throw new InternalServerErrorException("Document type not presented!");
        }

        List<Integer> applicationIds = globalReportDTO.getApplicationIds();
        Integer documentTypeId = globalReportDTO.getDocumentType();
        Integer calendarId = globalReportDTO.getCalendarId();
        List<RudiApplicationDTO> applications = new ArrayList<>();
        applicationIds.forEach(id -> applications.add(rudiApplicationService.selectById(id)));

        validateGlobalReportGenerate(applications, documentTypeId);

        Map<Integer, Map<String, Object>> applicationCustomValues = new HashMap<>();
        Map<Integer, Map<String, String>> applicationMetaData = new HashMap<>();
        fillMetadataAndCustomValues(applicationIds, documentTypeId, applicationCustomValues, applicationMetaData);

        GenerateReportFilter filter = new GenerateReportFilter(documentTypeId, ReportType.DOCX, applicationIds, applicationCustomValues, applicationMetaData,globalReportDTO.getIsDraft());
        GenerateReportsResult generateReportsResult = reportClient.uploadGlobalReport(filter);

        if (!globalReportDTO.getIsDraft()) {
            Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> movedReports = moveFiles(applications, generateReportsResult.getReports());
            saveAttachmentsOnGlobalReportGenerate(movedReports, documentTypeId, calendarId);
        }

        return generateReportsResult.getMergedReportsByTemplateName();
    }

    private Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> moveFiles(List<RudiApplicationDTO> applications, Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> reports) {
        Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> movedReports = new HashMap<>();
        for (Integer id : reports.keySet()) {
            RudiApplicationDTO application = applications.stream().filter(r -> r.getApplication().getId().equals(id)).findFirst().orElse(null);
            if (Objects.isNull(application)) {
                throw new InternalServerErrorException("Application not found");
            }
            for (GenerateReportsResult.ReportStoreDetailAndDocumentDetail report : reports.get(id)) {
                FileStoreEntryDTO entryAfterInit = initFileStoreEntryOnMove(report.getStoreLocation());
                FileStoreEntryBaseDTO movedFile = boAdminFileStoreClient.moveFile(FileConstants.RUDI_ROOT_DIRECTORY,
                        MinioBucketManager.buildAppsRelativePath(application.getApplication()), false, entryAfterInit);
                movedReports.computeIfAbsent(id, (k) -> new ArrayList<>());
                movedReports.get(id).add(new GenerateReportsResult.ReportStoreDetailAndDocumentDetail(movedFile, report.getDocumentDetail()));
            }
        }
        return movedReports;
    }

    private FileStoreEntryDTO initFileStoreEntryOnMove(FileStoreEntryBaseDTO tempEntry) {
        FileStoreEntryDTO newEntry = new FileStoreEntryDTO();
        newEntry.setFileId(tempEntry.getFileId());
        newEntry.setFileName(tempEntry.getFileName());
        newEntry.setRelativePath(tempEntry.getRelativePath());
        newEntry.setRootDirectory(tempEntry.getRootDirectory());
        return newEntry;
    }


    private void saveAttachmentsOnGlobalReportGenerate(Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> reports, Integer documentTypeId, Integer calendarId) {
        for (Integer id : reports.keySet()) {
            List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail> files = reports.get(id);
            AttachedDocDTO attachedDoc = initAttachmentOnGlobalReportGenerate(id, documentTypeId, files);
            AttachedDocDTO savedAttachment = saveAttachment(id, attachedDoc);
            commissionApplicationService.updateCommissionApplicationAttachedDoc(calendarId, id, savedAttachment);
        }
    }

    private AttachedDocDTO initAttachmentOnGlobalReportGenerate(Integer applicationId, Integer documentTypeId, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail> files) {
        if (CollectionUtils.isEmpty(files)) {
            throw new RuntimeException("Empty files list!");
        }
        AttachedDocDTO attachmentDoc = new AttachedDocDTO();
        attachmentDoc.setAttachedDocAttachments(new ArrayList<>());
        attachmentDoc.setDocumentType(new DocumentTypeDTO(documentTypeId));
        attachmentDoc.setDocCategory(new ReferenceDataDTO(ReferenceDataDomain.DOC_CATEGORY.domain(), DocCategory.APP_ATTACHMENTS.code()));
        for (GenerateReportsResult.ReportStoreDetailAndDocumentDetail file : files) {
            AttachmentDTO attachmentDTO = initSingleAttachment(file.getStoreLocation());
            attachmentDoc.getAttachedDocAttachments().add(new AttachedDocAttachmentDTO(null, attachmentDTO, file.getDocumentDetail().getDefaultAttachmentVisibility()));
        }

        return attachmentDoc;
    }

    private AttachmentDTO initSingleAttachment(FileStoreEntryBaseDTO file) {
        AttachmentDTO attachment = new AttachmentDTO();
        attachment.setFileName(file.getFileName());
        attachment.setFileSize(file.getFileSize().intValue());
        attachment.setContentType(file.getContentType());
        attachment.setFileLocation(file.getRelativePath().concat("/").concat(file.getFileId()));
        attachment.setBucketName(file.getRootDirectory());
        return attachment;
    }


    private void validateGlobalReportGenerate(List<RudiApplicationDTO> applications, Integer documentTypeId) {
        DocumentTypeDTO documentType = documentTypeClient.selectById(documentTypeId.toString());
        globalReportValidator.validate(applications, documentType);
    }

    private void fillMetadataAndCustomValues(List<Integer> applicationIds, Integer documentTypeId, Map<Integer, Map<String, Object>> applicationCustomValues, Map<Integer, Map<String, String>> applicationMetaData) {
        if (documentTypeId.equals(DocTypes.LETTER_TO_APPLICANT.code())) {
            return;
        }

        for (Integer applicationId : applicationIds) {
            Map<String, Object> customValues = new HashMap<>();
            Map<String, String> customMetadata = new HashMap<>();

            String certificateNumber = appReportCustomValuesService.getCertificateNumber(applicationId);
            String uuid = UUID.randomUUID().toString();

            customValues.put(MetadataKey.CERTIFICATE_NUMBER_KEY, certificateNumber);
            customValues.put(MetadataKey.CERTIFICATE_UUID_KEY, uuid);

            customMetadata.put(MetadataKey.CERTIFICATE_NUMBER_KEY, certificateNumber);
            customMetadata.put(MetadataKey.CERTIFICATE_UUID_KEY, uuid);

            applicationCustomValues.put(applicationId, customValues);
            applicationMetaData.put(applicationId, customMetadata);
        }

    }

}
