package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacid.payments.dto.enums.LiabilityModule;
import bg.duosoft.nacid.payments.dto.enums.LiabilityStatus;
import bg.duosoft.nacid.payments.dto.enums.PaymentStatus;
import bg.duosoft.nacid.payments.dto.nomenclatures.CurrencyDTO;
import bg.duosoft.nacid.payments.dto.nomenclatures.LiabilityCodeDTO;
import bg.duosoft.nacid.payments.dto.nomenclatures.ModuleDTO;
import bg.duosoft.nacid.payments.dto.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.payments.dto.payments.LiabilityDTO;
import bg.duosoft.nacid.payments.dto.payments.LiabilityDetailDTO;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CivilIdTypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntityPK;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomain;
import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidfrontofficedto.services.common.document.ApplicationReceiptDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApostilleApplicationDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidservicesbe.domain.entity.common.AppStatusHistoryEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationReceiptEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationFullEntity;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedFileMapper;
import bg.duosoft.nacidservicesbe.mapper.regprofapostille.RegprofApostilleApplicationMapper;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.regprof.RegprofApplicationFullRepository;
import bg.duosoft.nacidservicesbe.service.FileService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.RegprofApostilleService;
import bg.duosoft.nacidservicesbe.service.utils.ApplicationEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.AttachmentsEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.RegprofTrainingExperienceEntityUtils;
import bg.duosoft.nacidservicesbe.utils.AppNumberUtils;
import bg.duosoft.nacidservicesbe.utils.FileRelativePathUtils;
import bg.duosoft.nacidservicesbe.utils.PaymentConstants;
import bg.duosoft.nacidservicesbe.utils.PaymentUtils;
import bg.duosoft.nacidshareddata.service.report.ReportService;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.06.2023
 * Time: 14:35
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RegprofApostilleServiceImpl implements RegprofApostilleService {

    private final ApplicationRepository applicationRepository;
    private final RegprofApplicationFullRepository regprofApplicationFullRepository;

    private final RegprofApostilleApplicationMapper regprofApostilleApplicationMapper;
    private final AttachedFileMapper attachedFileMapper;

    private final FileService fileService;
    private final PaymentService paymentService;

    private final ReportService reportService;
    private final ApplicationReceiptMapper applicationReceiptMapper;

    @Override
    public RegprofApostilleApplicationDTO createRegprofApostilleApplication(RegprofApostilleApplicationDTO application) {
        RegprofApplicationFullEntity regprofApplication = regprofApostilleApplicationMapper.toEntity(application);
        List<ApplicationAttachedDocEntity> attachedDocs = regprofApplication.getApplication().getAttachedDocs();

        regprofApplication.getApplication().setAttachedDocs(null);
        regprofApplication.getApplication().setUserCreated(SecurityUtils.getUsername());
        createInitialStatusHistory(regprofApplication.getApplication(), application.getLastSubmissionDate(), Boolean.TRUE.equals(application.getESigned()));
        ApplicationEntityUtils.preSaveApplication(regprofApplication.getApplication());

        regprofApplication.setApplication(applicationRepository.save(regprofApplication.getApplication()));
        regprofApplication.setId(regprofApplication.getApplication().getId());
        String tempNum = AppNumberUtils.generateTempApplicationNumber(regprofApplication.getApplication().getApplicationTypeCode(), regprofApplication.getApplication().getId());
        regprofApplication.getApplication().setTempNumber(tempNum);
        regprofApplication.getApplication().setAttachedDocs(attachedDocs);
        moveFiles(regprofApplication.getApplication().getAttachedDocs(), regprofApplication.getId(), regprofApplication.getApplication().getDateCreated().toLocalDate());
        AttachmentsEntityUtils.preSaveAttachedDocs(regprofApplication.getApplication().getAttachedDocs(), regprofApplication.getId());

        RegprofTrainingExperienceEntityUtils.preSaveTrainingExperience(regprofApplication.getTrainingAndExperience().get(0));
        regprofApplication.getTrainingAndExperience().get(0).setRegprofApplicationId(regprofApplication.getId());

        RegprofApplicationFullEntity saved = regprofApplicationFullRepository.save(regprofApplication);
        CivilIdTypeEntity civilIdType = regprofApplicationFullRepository.getApplicantCivilIdTypeForApplication(saved.getId());
        saved.getApplication().getApplicant().setCivilIdType(civilIdType);
        RegprofApostilleApplicationDTO result = regprofApostilleApplicationMapper.toDto(saved);

        LiabilityDTO insertedLiability = paymentService.insertLiability(createRegprofApostilleLiability(application, result));
        updateAppWithLiabilityDetails(result, insertedLiability);

        return result;
    }

    @Override
    public void acceptRegprofApostilleApplication(AcceptApplicationRequestDTO acceptApplicationRequestDTO) {
        Optional<RegprofApplicationFullEntity> apostilleOpt = regprofApplicationFullRepository.findById(acceptApplicationRequestDTO.getApplicationId());
        if(apostilleOpt.isPresent() && apostilleOpt.get().getApostilleApplicationFlag() != null && apostilleOpt.get().getApostilleApplicationFlag() == 1) {
            RegprofApplicationFullEntity regprofApplicationFull = apostilleOpt.get();
            ApplicationEntity applicationToUpdate = regprofApplicationFull.getApplication();
            applicationToUpdate.setEntryNumber(acceptApplicationRequestDTO.getEntryNumber());
            applicationToUpdate.setEntryDate(acceptApplicationRequestDTO.getEntryDate());
            ApplicationEntityUtils.changeFoAppStatus(applicationToUpdate, acceptApplicationRequestDTO.getInitiatingUser(), FoApplicationStatus.ACCEPTED, null);
            RegprofApplicationFullEntity savedApplication = regprofApplicationFullRepository.save(regprofApplicationFull);
            ApplicationReceiptEntity receiptEntity = createReceiptEntity(savedApplication, FoApplicationStatus.ACCEPTED);
            ApplicationEntityUtils.addReceiptEntityToApplication(savedApplication.getApplication(), receiptEntity);
            regprofApplicationFullRepository.save(savedApplication);
        } else {
            throw new RuntimeException("Application does not exist or not an apostille app -> "+acceptApplicationRequestDTO.getApplicationId());
        }
    }

    @Override
    public byte[] regenerateReceipt(Integer id, boolean addToApplication, boolean keepOldReceipt) {
        FoApplicationStatus status = FoApplicationStatus.ACCEPTED;
        Optional<RegprofApplicationFullEntity> apostilleOpt = regprofApplicationFullRepository.findById(id);
        if(apostilleOpt.isPresent() && apostilleOpt.get().getApostilleApplicationFlag() != null && apostilleOpt.get().getApostilleApplicationFlag() == 1) {
            RegprofApplicationFullEntity applicationFull = apostilleOpt.get();
            Optional<ApplicationReceiptEntity> oldReceiptOpt = applicationFull.getApplication().getReceipts().stream().filter(rec -> rec.getActive().equals(1) && rec.getStatusCode().equals(status.getCode())).findFirst();
            if(oldReceiptOpt.isPresent()){
                if(addToApplication){
                    ApplicationReceiptEntity regeneratedReceipt = createReceiptEntity(applicationFull, status);
                    if(keepOldReceipt){
                        ApplicationEntityUtils.addReceiptEntityToApplication(applicationFull.getApplication(), regeneratedReceipt);
                        applicationRepository.save(applicationFull.getApplication());
                    } else {
                        String oldFileId = oldReceiptOpt.get().getFileId();
                        String oldFilePath = oldReceiptOpt.get().getRelativePath();
                        String oldFileRoot = oldReceiptOpt.get().getRootDirectory();

                        oldReceiptOpt.get().setFileId(regeneratedReceipt.getFileId());
                        oldReceiptOpt.get().setRelativePath(regeneratedReceipt.getRelativePath());
                        oldReceiptOpt.get().setRootDirectory(regeneratedReceipt.getRootDirectory());
                        applicationRepository.save(applicationFull.getApplication());
                        try {
                            fileService.removeFile(oldFileRoot, oldFilePath, oldFileId);
                        } catch (Exception e){
                            log.error("Residual receipt file remained in {}/{}/{}", oldFileRoot, oldFilePath, oldFileId);
                        }
                    }
                    return fileService.getFileContent(regeneratedReceipt.getRootDirectory(), regeneratedReceipt.getRelativePath(), regeneratedReceipt.getFileId(), null);
                } else {
                    byte[] receipt = createReceipt(applicationFull, status);
                    return receipt;
                }
            } else {
                throw new RuntimeException("No active old receipt for this FO status and this application: "+status+" "+id);
            }
        } else {
            throw new RuntimeException("Application does not exist or not an apostille app -> "+id);
        }
    }

    private ApplicationReceiptEntity createReceiptEntity(RegprofApplicationFullEntity applicationFullEntity, FoApplicationStatus status){
        byte[] receipt = createReceipt(applicationFullEntity, status);
        FileStoreEntryDTO receiptFile = fileService.uploadFile(FileConstants.FILE_GROUP_PDF, receipt, "receipt.pdf", "application/pdf", FileRelativePathUtils.createRelativeFilePath(applicationFullEntity.getApplication().getId(), applicationFullEntity.getApplication().getDateCreated().toLocalDate()));
        ApplicationReceiptDTO applicationReceiptDTO = new ApplicationReceiptDTO(receiptFile, status, true, LocalDateTime.now());
        ApplicationReceiptEntity receiptEntity = applicationReceiptMapper.toEntity(applicationReceiptDTO);
        return receiptEntity;
    }

    private byte[] createReceipt(RegprofApplicationFullEntity applicationFullEntity, FoApplicationStatus status){
        RegprofApostilleApplicationDTO application = regprofApostilleApplicationMapper.toDto(applicationFullEntity);
        try {
            LiabilityDTO liability = paymentService.getLiability(application.getTempNumber());
            updateAppWithLiabilityDetails(application, liability);
        } catch (Exception e){
            log.error("Could not get liability details for regprof application "+application.getTempNumber(), e);
        }
        byte[] receipt = reportService.generateReport(getApplicationReceiptTemplateName(), "bg", application, false, status.getCode());
        return receipt;
    }

    private void moveFiles(List<ApplicationAttachedDocEntity> attachedDocs, Integer id, LocalDate appDateCreated){
        attachedDocs.stream().forEach(att -> {
            FileStoreEntryDTO fileStoreEntry = fileService.moveFileToPersistentStore(FileConstants.SERVICES_ROOT_DIRECTORY, FileRelativePathUtils.createRelativeFilePath(id, appDateCreated), false, attachedFileMapper.toDto(att.getAttachment()));
            att.setAttachment(attachedFileMapper.toEntity(fileStoreEntry));
        });
    }

    private LocalDateTime createInitialStatusHistory(ApplicationEntity toSave, LocalDateTime lastSubmissionDate, boolean eSigned){
        if(toSave.getStatusHistory() == null) {
            toSave.setStatusHistory(new ArrayList<>());
        }
        AppStatusHistoryEntity hist = new AppStatusHistoryEntity();
        hist.setFoStatus(new ReferenceDataEntity());
        hist.getFoStatus().setPk(new ReferenceDataEntityPK(ReferenceDataDomain.FO_APP_STATUS.name(),
                eSigned? FoApplicationStatus.SUBMITTED_WITH_SIGNATURE.getCode() : FoApplicationStatus.SUBMITTED.getCode()));
        hist.setDateCreated(lastSubmissionDate);
        hist.setApplication(toSave);
        hist.setUserCreated(SecurityUtils.getUsername());
        toSave.getStatusHistory().add(hist);
        return hist.getDateCreated();
    }

    private LiabilityDTO createRegprofApostilleLiability(RegprofApostilleApplicationDTO originalRequest, RegprofApostilleApplicationDTO saved){
        LiabilityDTO liability = new LiabilityDTO();
        liability.setModule(new ModuleDTO(LiabilityModule.REGPROF.module()));
        liability.setApplicantNames(PaymentUtils.createPayerName(saved.getApplicantDetails().getApplicant().getNaturalPerson()));
        liability.setFrontOfficeReferenceNumber(saved.getTempNumber());
        liability.setStatus(new ReferenceDataDTO(PaymentConstants.NOM_LIABILITY_STATUSS, LiabilityStatus.ACTIVE.status()));
        liability.setFrontOfficeUser(SecurityUtils.getUsername());
        liability.setDescription("Професионални квалификации с Апостил");
        liability.setLiabilityDetails(new ArrayList<>());

        LiabilityDetailDTO detail = new LiabilityDetailDTO();
        detail.setDatePayment(originalRequest.getLastSubmissionDate());
        detail.setStatus(new ReferenceDataDTO(PaymentConstants.NOM_PAYMENT_STATUS, Boolean.TRUE.equals(originalRequest.getPaid()) ? PaymentStatus.PAID.status(): PaymentStatus.NOT_PAID.status()));
        detail.setAmount(originalRequest.getTotalFeesAmount());
        detail.setCurrency(new CurrencyDTO(originalRequest.getFeesCurrencyCode()));
        detail.setLiabilityCode(new LiabilityCodeDTO(PaymentConstants.LIABILITY_CODE_FOR_REGPROF));
        if(Boolean.TRUE.equals(originalRequest.getPaid())) {
            detail.setType(new ReferenceDataDTO(PaymentConstants.NOM_PAYMENT_TYPE, originalRequest.getPaymentTypeCode()));
        }

        liability.getLiabilityDetails().add(detail);

        return liability;
    }

    private void updateAppWithLiabilityDetails(RegprofApostilleApplicationDTO application, LiabilityDTO liability){
        if(liability != null && liability.getLiabilityDetails() != null && liability.getLiabilityDetails().size() > 0){
            LiabilityDetailDTO detail = liability.getLiabilityDetails().get(0);
            application.setPaid(detail.getStatus() != null && detail.getStatus().getId() != null && detail.getStatus().getId().equals(PaymentStatus.PAID.status()));
            application.setTotalFeesAmount(detail.getAmount());
            application.setFeesCurrencyCode(detail.getCurrency() != null? detail.getCurrency().getId(): null);
            if(detail.getType() != null) {
                application.setPaymentTypeCode(detail.getType().getId());
            }
        }
    }

    private String getApplicationReceiptTemplateName() {
        return "regprofApostilleReceipt.ftl";
    }
}
