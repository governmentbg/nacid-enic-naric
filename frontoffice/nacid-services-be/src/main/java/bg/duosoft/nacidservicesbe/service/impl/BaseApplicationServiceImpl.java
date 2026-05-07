package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CApplicationAcceptedEmailData;
import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CApplicationRevertedDraftEmailData;
import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CApplicationSubmittedEmailData;
import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CLibservAppSubmissionEmailData;
import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ApplicationSubtypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.ApplicationReceiptDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithServiceType;
import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.*;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.SignedApplicationDocumentMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationAttachedDocsRepository;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationRepository;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.FileService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.utils.FileRelativePathUtils;
import bg.duosoft.nacidservicesbe.utils.PaymentUtils;
import bg.duosoft.nacidshareddata.service.report.ReportService;
import bg.duosoft.nacidservicesbe.service.utils.ApplicationEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.AttachmentsEntityUtils;
import bg.duosoft.nacidservicesbe.utils.AppNumberUtils;
import bg.duosoft.nacidservicesbe.utils.AppStatusUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 18:13
 */
@Getter
@Slf4j
@Transactional
public abstract class BaseApplicationServiceImpl<A extends CommonApplicationDTO, AD extends CommonApplicantDetailsDTO, SD, FAE extends FullApplicationEntityBase> implements BaseApplicationService<A, AD, SD> {

    @Autowired
    private AttachedDocumentMapper attachedDocumentMapper;

    @Autowired
    private ApplicationReceiptMapper applicationReceiptMapper;

    @Autowired
    private ReferenceDataMapper referenceDataMapper;

    @Autowired
    private SignedApplicationDocumentMapper signedApplicationDocumentMapper;

    @Autowired
    private ApplicationAttachedDocsRepository applicationAttachedDocsRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Override
    public A getApplication(Integer id) {
        if(!applicationRepository.getApplicationSubtypeCode(id).equals(getInitialApplicationSubtype().getCode())){
            return null;
        }
        Optional<FAE> fetchedFromDBOpt = getFullApplicationRepository().findById(id);
        if(fetchedFromDBOpt.isPresent()){
            return getFullApplicationMapper().toDto(fetchedFromDBOpt.get());
        }
        return null;
    }

    @Override
    public void deleteApplication(Integer id) {
        Optional<FAE> fetchedFromDBOpt = getFullApplicationRepository().findById(id);
        if(fetchedFromDBOpt.isPresent()){
            List<ApplicationAttachedDocEntity> attachments = fetchedFromDBOpt.get().getApplication().getAttachedDocs();
            getFullApplicationRepository().deleteById(id);
            if(attachments != null && attachments.size() > 0){
                attachments.stream().forEach(att -> {
                    fileService.removeFile(att.getAttachment().getRootDirectory(), att.getAttachment().getRelativePath(), att.getAttachment().getFileId());
                });
            }
        }
    }

    @Override
    public A getApplicationForCheckup(String dossierNumber, String accessCode) {
        Object[] dossierParts = AppNumberUtils.breakDossierNumber(dossierNumber);

        Optional<FAE> fetchedFromDBOpt = getFullApplicationRepository().findByApplication_EntryNumberAndApplicationEntryDateAndApplication_AccessCode(
                (String)dossierParts[0], (LocalDate)dossierParts[1], accessCode
        );
        if(fetchedFromDBOpt.isPresent() && AppStatusUtils.getCheckupAllowedStatusCodes().contains(fetchedFromDBOpt.get().getApplication().getFoStatusCode())){
            return getFullApplicationMapper().toDto(fetchedFromDBOpt.get());
        }
        return null;
    }

    @Override
    public AD saveApplicantDetails(Integer applicationId, AD applicantDetails) {
        ApplicationEntity toSave = getApplicantDetailsMapper().toEntity(applicantDetails);
        ApplicationEntity saved;
        if(applicationId != null){
            ApplicationEntity fetchedFromDB = applicationRepository.findById(applicationId).orElse(null);

            ApplicationEntityUtils.keepDBApplicationDetails(fetchedFromDB, toSave);
            ApplicationEntityUtils.preSaveApplication(toSave);
            saved = applicationRepository.save(toSave);
        } else {
            toSave.setDateCreated(LocalDateTime.now());
            toSave.setUserCreated(SecurityUtils.getUsername());

            toSave.setApplicationTypeCode(getInitialApplicationType().getCode());
            toSave.setApplicationSubtype(new ApplicationSubtypeEntity());
            toSave.getApplicationSubtype().setId(getInitialApplicationSubtype().getCode());

            ApplicationEntityUtils.preSaveApplication(toSave);

            ApplicationEntityUtils.changeFoAppStatus(toSave, SecurityUtils.getUsername(), FoApplicationStatus.DRAFT, null);

            saved = applicationRepository.save(toSave);
            String tempNum = AppNumberUtils.generateTempApplicationNumber(getInitialApplicationType().getCode(), saved.getId());
            saved.setTempNumber(tempNum);
            saved = applicationRepository.save(toSave);
            createNewRequest(saved.getId());
        }
        return getApplicantDetailsMapper().toDto(saved);
    }

    @Override
    public DocumentDetailsDTO saveDocumentDetails(Integer applicationId, DocumentDetailsDTO documentDetails) {
        List<ApplicationAttachedDocEntity> attachmentsToSave = attachedDocumentMapper.toEntityList(documentDetails.getAttachments());
        AttachmentsEntityUtils.preSaveAttachedDocs(attachmentsToSave, applicationId);
        applicationAttachedDocsRepository.removeAllById_ApplicationId(applicationId);
        List<ApplicationAttachedDocEntity> saved = applicationAttachedDocsRepository.saveAll(attachmentsToSave);

        DocumentDetailsDTO toReturn = attachedDocumentMapper.toDocumentDetailsDtoFromEntityList(saved);
        return toReturn;
    }

    @Override
    public List<String> finalizeApplication(Integer id) {
        List<String> appsTempNumbers = new ArrayList<>();
        List<FAE> entitiesToSave = prepareEntitiesToSaveOnFileFinalize(id);
        checkIfAppIsOkToBeFiledOrFinalized(id, entitiesToSave);
        boolean isMultiple = entitiesToSave.size() > 1;
        try {
            MultipleApplicationEntity multipleApplication = isMultiple ? new MultipleApplicationEntity() : null;
            for (FAE fullApp : entitiesToSave) {
                if(fullApp.getApplication().getMultipleApplication() == null) {
                    fullApp.getApplication().setMultipleApplication(multipleApplication);
                }
                if (!StringUtils.hasText(fullApp.getApplication().getTempNumber())) {
                    String tempNum = AppNumberUtils.generateTempApplicationNumber(getInitialApplicationType().getCode(), fullApp.getApplication().getId());
                    fullApp.getApplication().setTempNumber(tempNum);
                }
                ApplicationEntity applicationEntity = getFullApplicationRepository().save(fullApp).getApplication();
                CalculatedFeesDTO calculatedFees = calculateFees(fullApp.getId());
                ApplicationReceiptEntity receiptEntity = createReceiptEntity(fullApp.getId(), fullApp.getApplication().getDateCreated().toLocalDate(), FoApplicationStatus.FINALIZED, calculatedFees);
                ApplicationEntityUtils.changeFoAppStatus(applicationEntity, SecurityUtils.getUsername(), FoApplicationStatus.FINALIZED, null);
                ApplicationEntityUtils.addReceiptEntityToApplication(applicationEntity, receiptEntity);
                ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
                appsTempNumbers.add(savedApplication.getTempNumber());
            }
            return appsTempNumbers;
        } catch (Exception e){
            cleanUpClonedFiles(entitiesToSave);
            throw e;
        }
    }

    public List<String> fileApplicationInternal(Integer id) {
        List<String> appsTempNumbers = new ArrayList<>();
        List<FAE> entitiesToSave = prepareEntitiesToSaveOnFileFinalize(id);
        checkIfAppIsOkToBeFiledOrFinalized(id, entitiesToSave);
        boolean isMultiple = entitiesToSave.size() > 1;
        try {
            MultipleApplicationEntity multipleApplication = isMultiple ? new MultipleApplicationEntity() : null;
            for (FAE fullApp : entitiesToSave) {
                boolean appIsNew = false;
                if(fullApp.getApplication().getMultipleApplication() == null) {
                    fullApp.getApplication().setMultipleApplication(multipleApplication);
                }
                if (!StringUtils.hasText(fullApp.getApplication().getTempNumber())) {
                    String tempNum = AppNumberUtils.generateTempApplicationNumber(getInitialApplicationType().getCode(), fullApp.getApplication().getId());
                    fullApp.getApplication().setTempNumber(tempNum);
                    appIsNew = true;
                }
                ApplicationEntity applicationEntity = getFullApplicationRepository().save(fullApp).getApplication();
                CalculatedFeesDTO calculatedFees = insertFeesIntoPayments(applicationEntity.getId());
                try {
                    if(applicationEntity.getReceipts() != null) {
                        Optional<ApplicationReceiptEntity> finalizedOpt = applicationEntity.getReceipts().stream().filter(rc -> rc.getActive().equals(1) && rc.getStatusCode().equals(FoApplicationStatus.FINALIZED.getCode())).findFirst();
                        if (finalizedOpt.isPresent()) {
                            finalizedOpt.get().setActive(0);
                        }
                    }
                    ApplicationReceiptEntity receiptEntity = createReceiptEntity(fullApp.getId(), fullApp.getApplication().getDateCreated().toLocalDate(), FoApplicationStatus.SUBMITTED, calculatedFees);
                    ApplicationEntityUtils.changeFoAppStatus(applicationEntity, SecurityUtils.getUsername(), FoApplicationStatus.SUBMITTED, null);
                    ApplicationEntityUtils.addReceiptEntityToApplication(applicationEntity, receiptEntity);
                    applicationEntity.setAccessCode(AppNumberUtils.generateApplicationAccessCode());
                    applicationEntity.setSignedFlag(0);
                    ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
                    appsTempNumbers.add(savedApplication.getTempNumber());
                    sendApplicationSubmittedMails(savedApplication);
                } catch (Exception e){
                    if(appIsNew){
                        paymentService.deleteFeesFromPayments(fullApp.getApplication().getTempNumber());
                    }
                    throw e;
                }
            }

            //TODO may be think about cleaning residual files (if any)
            //TODO what about paid flag?

            return appsTempNumbers;
        } catch (Exception e){
            cleanUpClonedFiles(entitiesToSave);
            throw e;
        }
    }

    private void checkIfAppIsOkToBeFiledOrFinalized(Integer id, List<FAE> entitiesToSave){
        if(entitiesToSave.size() > 1 && Boolean.TRUE.equals(getApplication(id).getSubmittedOrFinalized())){
            throw new RuntimeException("Can not submit application with multiple apps inside if it was already submitted or finalized");
        }
    }

    private void cleanUpClonedFiles(List<FAE> entitiesList){
        entitiesList.stream().filter(e -> !e.getApplication().isOriginal()).forEach(e -> {
            if(e.getApplication().getAttachedDocs() != null){
                e.getApplication().getAttachedDocs().stream().forEach(att -> {
                    fileService.removeFile(att.getAttachment().getRootDirectory(), att.getAttachment().getRelativePath(), att.getAttachment().getFileId());
                });
            }
        });
    }

    public String fileSignedApplicationInternal(Integer id, SignedApplicationDocumentDTO signedApplication) {
        ApplicationReceiptEntity receiptEntity = signedApplicationDocumentMapper.toEntity(signedApplication);
        ApplicationEntity applicationEntity = getApplicationEntityById(id);
        ApplicationEntityUtils.changeFoAppStatus(applicationEntity, SecurityUtils.getUsername(), FoApplicationStatus.SUBMITTED_WITH_SIGNATURE, null);
        ApplicationEntityUtils.addReceiptEntityToApplication(applicationEntity, receiptEntity);
        applicationEntity.setAccessCode(AppNumberUtils.generateApplicationAccessCode());
        applicationEntity.setSignedFlag(1);
        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
        insertFeesIntoPayments(id);
        sendApplicationSubmittedMails(savedApplication);

        //TODO may be think about cleaning residual files (if any)
        //TODO what about paid flag?

        return savedApplication.getTempNumber();
    }

    @Override
    public byte[] changeApplicationToAccepted(AcceptApplicationRequestDTO acceptApplicationRequestDTO) {
        ApplicationEntity applicationToSave = getApplicationEntityById(acceptApplicationRequestDTO.getApplicationId());
        applicationToSave.setEntryNumber(acceptApplicationRequestDTO.getEntryNumber());
        applicationToSave.setEntryDate(acceptApplicationRequestDTO.getEntryDate());
        ApplicationEntityUtils.changeFoAppStatus(applicationToSave, acceptApplicationRequestDTO.getInitiatingUser(), FoApplicationStatus.ACCEPTED, null);
        ApplicationEntity updated = applicationRepository.save(applicationToSave);

        ApplicationReceiptEntity receiptEntity = createReceiptEntity(updated.getId(), updated.getDateCreated().toLocalDate(), FoApplicationStatus.ACCEPTED, calculateFees(updated.getId()));
        ApplicationEntityUtils.addReceiptEntityToApplication(updated, receiptEntity);
        applicationRepository.save(updated);
        NacidUserDetailsDTO applicationUser = keycloakUserService.getUserByUsername(applicationToSave.getUserCreated());
        mailSenderService.sendApplicationAcceptedMail(CApplicationAcceptedEmailData.builder()
                    .tempNumber(updated.getTempNumber())
                    .email(applicationUser.getEmail())
                    .fullName(applicationUser.getFirstAndLastName())
                    .entryNumber(updated.getEntryNumber())
                    .entryDate(updated.getEntryDate().format(DateTimeFormatter.ofPattern(DTOConstants.DATE_FORMAT)))
                    .accessCode(updated.getAccessCode())
                .build());
        return fileService.getFileContent(receiptEntity.getRootDirectory(), receiptEntity.getRelativePath(), receiptEntity.getFileId(), null);
    }

    @Override
    public void changeFoApplicationStatus(ChangeFoApplicationStatusRequestDTO changeStatusRequest) {
        ApplicationEntity applicationToSave = getApplicationEntityById(changeStatusRequest.getApplicationId());
        FoApplicationStatus applicationStatus =
            switch (changeStatusRequest.getStatusChangeType()){
                case ACCEPTANCE_DENIED -> FoApplicationStatus.ACCEPTANCE_DENIED;
                case REVERT_ACCEPTANCE_DENIED -> FoApplicationStatus.fromCode(ApplicationEntityUtils.getLastSubmitStatus(applicationToSave));
            };
        ApplicationEntityUtils.changeFoAppStatus(applicationToSave, changeStatusRequest.getInitiatingUser(), applicationStatus, changeStatusRequest.getMessage());
        applicationRepository.save(applicationToSave);
    }

    @Override
    public void revertApplicationToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        ApplicationEntity applicationToSave = getApplicationEntityById(revertApplicationStatusToDraftRequest.getApplicationId());
        applicationToSave.setAccessCode(null);
        ApplicationEntityUtils.changeFoAppStatus(applicationToSave, revertApplicationStatusToDraftRequest.getInitiatingUser(), FoApplicationStatus.DRAFT, revertApplicationStatusToDraftRequest.getRevertMessage());
        applicationToSave.getReceipts().forEach(rec -> rec.setActive(0));
        applicationToSave.setSignedFlag(0);
        applicationRepository.save(applicationToSave);
        NacidUserDetailsDTO applicationUser = keycloakUserService.getUserByUsername(applicationToSave.getUserCreated());
        mailSenderService.sendApplicationRevertedDraftMail(CApplicationRevertedDraftEmailData.builder()
                    .email(applicationUser.getEmail())
                    .fullName(applicationUser.getFirstAndLastName())
                    .tempNumber(applicationToSave.getTempNumber())
                    .reason(revertApplicationStatusToDraftRequest.getRevertMessage())
                .build());
        //TODO what about paid flag?
    }

    @Override
    public byte[] regenerateReceipt(Integer id, boolean addToApplication, boolean keepOldReceipt, FoApplicationStatus status) {
        ApplicationEntity applicationEntity = getApplicationEntityById(id);
        Optional<ApplicationReceiptEntity> oldReceiptOpt = applicationEntity.getReceipts().stream().filter(rec -> rec.getActive().equals(1) && rec.getStatusCode().equals(status.getCode())).findFirst();
        CalculatedFeesDTO calculatedFees = calculateFees(id);
        if(oldReceiptOpt.isPresent()){
            if(addToApplication){
                ApplicationReceiptEntity regeneratedReceipt = createReceiptEntity(id, applicationEntity.getDateCreated().toLocalDate(), status, calculatedFees);
                if(keepOldReceipt){
                    ApplicationEntityUtils.addReceiptEntityToApplication(applicationEntity, regeneratedReceipt);
                    applicationRepository.save(applicationEntity);
                } else {
                    String oldFileId = oldReceiptOpt.get().getFileId();
                    String oldFilePath = oldReceiptOpt.get().getRelativePath();
                    String oldFileRoot = oldReceiptOpt.get().getRootDirectory();

                    oldReceiptOpt.get().setFileId(regeneratedReceipt.getFileId());
                    oldReceiptOpt.get().setRelativePath(regeneratedReceipt.getRelativePath());
                    oldReceiptOpt.get().setRootDirectory(regeneratedReceipt.getRootDirectory());
                    applicationRepository.save(applicationEntity);
                    try {
                        fileService.removeFile(oldFileRoot, oldFilePath, oldFileId);
                    } catch (Exception e){
                        log.error("Residual receipt file remained in {}/{}/{}", oldFileRoot, oldFilePath, oldFileId);
                    }
                }
                return fileService.getFileContent(regeneratedReceipt.getRootDirectory(), regeneratedReceipt.getRelativePath(), regeneratedReceipt.getFileId(), null);
            } else {
                byte[] receipt = createReceipt(id, status, calculatedFees);
                return receipt;
            }
        } else {
            throw new RuntimeException("No active old receipt for this FO status and this application: "+status+" "+id);
        }
    }

    private ApplicationReceiptEntity createReceiptEntity(Integer applicationId, LocalDate applicationCreationDate, FoApplicationStatus status, CalculatedFeesDTO fees){
        byte[] receipt = createReceipt(applicationId, status, fees);
        FileStoreEntryDTO receiptFile = fileService.uploadFile(FileConstants.FILE_GROUP_PDF, receipt, "receipt.pdf", "application/pdf", FileRelativePathUtils.createRelativeFilePath(applicationId, applicationCreationDate));
        ApplicationReceiptDTO applicationReceiptDTO = new ApplicationReceiptDTO(receiptFile, status, true, LocalDateTime.now());
        ApplicationReceiptEntity receiptEntity = applicationReceiptMapper.toEntity(applicationReceiptDTO);
        return receiptEntity;
    }

    private byte[] createReceipt(Integer applicationId, FoApplicationStatus status, CalculatedFeesDTO fees){
        A application = getApplication(applicationId);
        if(application == null){
            throw new RuntimeException("Application does not exist");
        }
        byte[] receipt = reportService.generateReport(getApplicationReceiptTemplateName(), "bg", application, false, status.getCode(), fees);
        return receipt;
    }

    public ReferenceDataDTO saveServiceType(Integer applicationId, WithServiceType withServiceType){
        ReferenceDataDTO savedServiceTypeDto = null;
        if(withServiceType.getServiceType() != null){
            ReferenceDataEntity serviceTypeToSave = referenceDataMapper.toEntity(withServiceType.getServiceType());
            ApplicationEntity applicationEntityToUpdate = getApplicationEntityById(applicationId);
            applicationEntityToUpdate.setServiceType(serviceTypeToSave);
            ReferenceDataEntity savedServiceType = applicationRepository.save(applicationEntityToUpdate).getServiceType();
            savedServiceTypeDto = referenceDataMapper.toDto(savedServiceType);
        }
        return savedServiceTypeDto;
    }

    @Override
    public List<EvaluationDTO> evaluateApplication(Integer id) {
        A application = getApplication(id);
        return getEvaluationsComponent().evaluateApplication(application);
    }

    @Override
    public CalculatedFeesDTO calculateFees(Integer id) {
        A app = getApplication(id);
        Map<String, String> params = createFeeCalculationParamsMap(app);
        CalculatedFeesDTO fees = paymentService.getCalculatedFees(params, getPaymentModule());
        return fees;
    }

    private CalculatedFeesDTO insertFeesIntoPayments(Integer id){
        A app = getApplication(id);
        Map<String, String> params = createFeeCalculationParamsMap(app);

        CalculatedFeesDTO fees = paymentService.insertFeesForPayment(params, getPaymentModule(), app.getTempNumber(), app.getUserCreated(), app.getApplicationSubtypeName(), getPayerName(app));
        return fees;
    }

    @Override
    public String getPayerName(A app){
        return PaymentUtils.createPayerName(app);
    }

    private ApplicationEntity getApplicationEntityById(Integer id){
        Optional<ApplicationEntity> opt = applicationRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new RuntimeException("No such application "+id);
    }

    private void sendApplicationSubmittedMails(ApplicationEntity application){
        String userName = SecurityUtils.getClaim(SecurityUtils.FIRST_NAME_CLAIM).toString() + " "+SecurityUtils.getClaim(SecurityUtils.LAST_NAME_CLAIM).toString();
        mailSenderService.sendApplicationSubmittedMail(CApplicationSubmittedEmailData.builder()
                    .email(SecurityUtils.getEmail())
                    .fullName(userName)
                    .tempNumber(application.getTempNumber())
                .build());
        if(application.getApplicationTypeCode().equals(ApplicationType.LIBRARY.getCode())){
            mailSenderService.sendLibservAppSubmissionMail(CLibservAppSubmissionEmailData.builder()
                            .appNumber(application.getTempNumber())
                            .appType(application.getApplicationSubtype().getName())
                            .userFullName(userName)
                            .username(SecurityUtils.getUsername())
                    .build());
        }
    }

    @Override
    public boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        return true;
    }

    public abstract ApplicationType getInitialApplicationType();
    public abstract ApplicationSubtype getInitialApplicationSubtype();
    public abstract void createNewRequest(Integer applicationId);
    public abstract BaseObjectMapper<ApplicationEntity, AD> getApplicantDetailsMapper();
    public abstract String getApplicationReceiptTemplateName();
    public abstract FullApplicationRepositoryBase<FAE> getFullApplicationRepository();
    public abstract BaseObjectMapper<FAE, A> getFullApplicationMapper();
    public abstract BaseApplicationEvaluations<A> getEvaluationsComponent();
    public abstract List<FAE> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId);
}
