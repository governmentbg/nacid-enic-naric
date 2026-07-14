package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomain;
import bg.duosoft.nacidfrontofficedto.services.common.WithFile;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidcoredata.util.FileUtils;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidservicesbe.controller.utils.AccessUtils;
import bg.duosoft.nacidservicesbe.service.*;
import bg.duosoft.nacidservicesbe.utils.FileRelativePathUtils;
import bg.duosoft.nacidservicesbe.validation.ApplicationCheckupRequestValidator;
import bg.duosoft.nacidservicesbe.validation.common.documents.DocumentDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.common.documents.SignedApplicationDocumentValidator;
import bg.duosoft.nacidshareddata.service.report.ReportService;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 19.12.2022
 * Time: 12:16
 */
@Slf4j
@Getter
public abstract class BaseApplicationController<R extends CommonApplicationDTO, A extends CommonApplicantDetailsDTO, S> {

    @Autowired
    private DocumentDetailsValidator documentDetailsValidator;

    @Autowired
    private FileService fileService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ApplicationCheckupRequestValidator applicationCheckupRequestValidator;

    @Autowired
    private SignedApplicationDocumentValidator signedApplicationDocumentValidator;

    @Autowired
    private BoPublicServicesService boPublicServicesService;

    @Autowired
    private CommonApplicationService commonApplicationService;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommonApplicationDTO> getApplication(@PathVariable Integer id){
        AccessUtils.checkAccessAllowedForAppView(id, commonApplicationService);
        CommonApplicationDTO app = getApplicationService().getApplication(id);
        if(app != null) {
            return ResponseEntity.ok().body(app);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteApplication(@PathVariable Integer id){
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppDeletionAllowedDependingOnStatuses(id, commonApplicationService);
        getApplicationService().deleteApplication(id);
    }

    @GetMapping("/checkup")
    public ResponseEntity<CommonApplicationDTO> getApplicationForCheckup(HttpServletRequest request,
                                                                         ApplicationCheckupRequestDTO checkupRequest){
        checkupRequest.setRemoteIp(request.getRemoteAddr());
        BadRequestValidator.validateRequest(applicationCheckupRequestValidator, checkupRequest);
        CommonApplicationDTO app = getApplicationService().getApplicationForCheckup(checkupRequest.getDossierNumber(), checkupRequest.getAccessCode());
        if(app != null) {
            return ResponseEntity.ok().body(app);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save-applicant-details")
    @PreAuthorize("isAuthenticated()")
    public A saveApplicantDetails(@RequestParam(required = false) Integer id, @RequestBody A applicantDetails){
        if(id != null) {
            AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
            AccessUtils.checkAppModificationAllowedDependingOnStatus(id, commonApplicationService);
        }
        BadRequestValidator.validateRequest(getApplicantDetailsValidator(), applicantDetails);
        preSaveApplicantDetails(applicantDetails);
        return (A) getApplicationService().saveApplicantDetails(id, applicantDetails);
    }

    @PostMapping("/save-app-specific-details")
    @PreAuthorize("isAuthenticated()")
    public S saveAppSpecificDetails(@RequestParam Integer id, @RequestBody S specificDetails){
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppModificationAllowedDependingOnStatus(id, commonApplicationService);
        BadRequestValidator.validateRequest(getApplicationSpecificDetailsValidator(), specificDetails, id);
        preSaveRequestSpecificDetails(specificDetails);
        return (S) getApplicationService().saveRequestSpecificDetails(id, specificDetails);
    }

    @PostMapping("/save-document-details")
    @PreAuthorize("isAuthenticated()")
    public DocumentDetailsDTO saveDocumentDetails(@RequestParam Integer id, @RequestBody DocumentDetailsDTO documentDetails){
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppModificationAllowedDependingOnStatus(id, commonApplicationService);
        BadRequestValidator.validateRequest(documentDetailsValidator, documentDetails);
        DocumentDetailsDTO documentDetailsWithoutRemoved = new DocumentDetailsDTO();
        documentDetailsWithoutRemoved.setAttachments(documentDetails.getAttachments().stream().filter(attachment -> !attachment.isForRemoval()).collect(Collectors.toList()));
        moveNotPersistedFiles(documentDetailsWithoutRemoved.getAttachments(), createApplicationFileStoreRelativePath(id));
        preSaveDocumentDetails(documentDetailsWithoutRemoved);
        DocumentDetailsDTO saved = getApplicationService().saveDocumentDetails(id, documentDetailsWithoutRemoved);
        try {
            removeUnnecessaryFiles(documentDetails.getAttachments());
        } catch (Exception e){
            log.error("Residual files might not been removed correctly when saving document details", e);
        }
        return saved;
    }

    @PostMapping("/finalize-application")
    @PreAuthorize("isAuthenticated()")
    public List<String> finalizeApplication(@RequestParam Integer id) {
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppModificationAllowedDependingOnStatus(id, commonApplicationService);
        BadRequestValidator.validateRequest(getFilingValidator(), getApplicationService().getApplication(id));
        return getApplicationService().finalizeApplication(id);
    }

    @PostMapping("/file-application")
    @PreAuthorize("isAuthenticated()")
    public List<String> fileApplication(@RequestParam Integer id) {
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppFilingAllowedDependingOnStatus(id, commonApplicationService);
        BadRequestValidator.validateRequest(getFilingValidator(), getApplicationService().getApplication(id));
        return getApplicationService().fileApplication(id);
    }

    @PostMapping("/file-signed-application")
    @PreAuthorize("isAuthenticated()")
    public String fileSignedApplication(@RequestParam Integer id, @RequestBody SignedApplicationDocumentDTO signedApplicationDocument) {
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppFilingSignedAllowedDependingOnStatus(id, commonApplicationService);
        BadRequestValidator.validateRequest(signedApplicationDocumentValidator, signedApplicationDocument);
        FileStoreEntryDTO tempFile = signedApplicationDocument.getFile();
        FileStoreEntryDTO persistedFile = fileService.moveFileToPersistentStore(FileConstants.SERVICES_ROOT_DIRECTORY, createApplicationFileStoreRelativePath(id), false, signedApplicationDocument.getFile());
        signedApplicationDocument.setFile(persistedFile);
        String filedResult = getApplicationService().fileSignedApplication(id, signedApplicationDocument);
        try {
            fileService.removeFile(tempFile.getRootDirectory(), tempFile.getRelativePath(), tempFile.getFileId());
        } catch (Exception e){
            log.error("Residual files remained in temp {}/{}/{}", tempFile.getRootDirectory(), tempFile.getRelativePath(), tempFile.getFileId());
            log.error("Error removing file", e);
        }
        return filedResult;
    }

    @GetMapping("/generate-draft-receipt")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<byte[]> generateDraftReceipt(@RequestParam Integer id){
        AccessUtils.checkAccessAllowedForAppView(id, commonApplicationService);
        CommonApplicationDTO application = getApplicationService().getApplication(id);
        if(application != null) {
            String templateName = getApplicationService().getApplicationReceiptTemplateName();
            byte[] report = reportService.generateReport(templateName, "bg", application, true, FoApplicationStatus.DRAFT.getCode(), null);
            return ResponseEntity.ok()
                    .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", "receipt.pdf", "receipt.pdf"))
                    .contentType(MediaType.APPLICATION_PDF).body(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/evaluate-application")
    @PreAuthorize("isAuthenticated()")
    public List<EvaluationDTO> evaluateApplication(@RequestParam Integer id) {
        AccessUtils.checkAccessAllowedForAppView(id, commonApplicationService);
        return getApplicationService().evaluateApplication(id);
    }

    @GetMapping("/calculate-fees")
    @PreAuthorize("isAuthenticated()")
    public CalculatedFeesDTO calculateFees(@RequestParam Integer id) {
        AccessUtils.checkAccessAllowedForAppView(id, commonApplicationService);
        return getApplicationService().calculateFees(id);
    }

    public static void preSaveDocumentDetails(DocumentDetailsDTO documentDetails){
        if(documentDetails != null && documentDetails.getAttachments() != null){
            documentDetails.getAttachments().stream().forEach(attachment -> {
                if(attachment.getAttachmentForm() != null && !StringUtils.hasText(attachment.getAttachmentForm().getId())){
                    attachment.setAttachmentForm(null);
                }
                if(attachment.getAttachmentForm() != null && StringUtils.hasText(attachment.getAttachmentForm().getId())
                    && !StringUtils.hasText(attachment.getAttachmentForm().getDomain())){
                    attachment.getAttachmentForm().setDomain(ReferenceDataDomain.COPY_TYPE.name());
                }
                if(attachment.getAttachmentType() != null && attachment.getAttachmentType().getId() == null){
                    attachment.setAttachmentType(null);
                }
            });
        }
    }

    public void moveNotPersistedFiles(List<? extends WithFile> withFileList, String applicationRelativePath){
        if(withFileList != null){
            withFileList.stream().forEach(withFile -> {
                if(withFile.getFile() != null && !FileUtils.isFilePersisted(withFile.getFile())){
                    FileStoreEntryDTO persisted = fileService.moveFileToPersistentStore(FileConstants.SERVICES_ROOT_DIRECTORY, applicationRelativePath, false, withFile.getFile());
                    withFile.setFile(persisted);
                }
            });
        }
    }

    public void removeUnnecessaryFiles(List<? extends WithFile> withFileList){
        if(withFileList != null){
            withFileList.stream().filter(withFile -> withFile.isForRemoval() || (withFile.getFile() != null && !FileUtils.isFilePersisted(withFile.getFile()))).forEach(attachment -> {
                if(attachment.getFile() != null && StringUtils.hasText(attachment.getFile().getFileId())) {
                    fileService.removeFile(attachment.getFile().getRootDirectory(), attachment.getFile().getRelativePath(), attachment.getFile().getFileId());
                }
            });
        }
    }

    protected String createApplicationFileStoreRelativePath(Integer id){
        LocalDate appDateCreated = commonApplicationService.getApplicationDateCreated(id);
        return FileRelativePathUtils.createRelativeFilePath(id, appDateCreated);
    }

    public void preSaveApplicantDetails(A applicantDetails) {};

    public void preSaveRequestSpecificDetails(S specificDetails){}

    public abstract BaseApplicationService getApplicationService();
    public abstract Validator<A> getApplicantDetailsValidator();
    public abstract Validator<S> getApplicationSpecificDetailsValidator();
    public abstract Validator<R> getFilingValidator();

    public BoPublicServicesService getBoPublicServicesService() {
        return boPublicServicesService;
    }
}
