package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.client.client.fileStore.BoAdminFileStoreClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioFilePath;
import bg.duosoft.nacidbackofficeshareddata.service.ApplicationCertificatesService;
import bg.duosoft.nacidbackofficeshareddata.service.AttachedDocService;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationAttachmentService;
import bg.duosoft.nacidbackofficeshareddata.validator.DeleteAttachmentValidator;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.util.appreport.MetadataKey;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class BaseApplicationAttachmentServiceImpl implements BaseApplicationAttachmentService {
    @Autowired
    @Lazy
    protected BoAdminFileStoreClient boAdminFileStoreClient;

    @Autowired
    protected ApplicationCertificatesService applicationCertificatesService;

    @Autowired
    protected AttachedDocService attachedDocService;

    @Autowired
    protected DeleteAttachmentValidator deleteAttachmentValidator;
    @Autowired
    protected AbdocsAdminService abdocsAdminService;


    //TODO N22-393 I don't know how this method should look like after the new changes, so I will just make it compilable, but it has to be fixed later !
    @Override
    public UpdateAttachmentsResultDTO attachmentUpdates(ApplicationDTO application, AttachedDocDTO attachment) {
        if (Objects.isNull(attachment) || CollectionUtils.isEmpty(attachment.getAttachedDocAttachments())) {
            throw new ValidationErrorException(List.of(ValidationError.create("attachedDocAttachments","attachedDocAttachments.empty")));
        }
        initAttachmentsAndCertificates(application);
        List<AttachedDocDTO> existedAttachments = application.getAttachments();
        List<ApplicationCertificatesDTO> existedCertificates = application.getCertificates();

        Pair<String, String> certificateNumberAndUUIDFromFile = getCertificateNumberAndUUIDFromFile(attachment);

        boolean isNewCertificate = Objects.nonNull(certificateNumberAndUUIDFromFile) && Objects.isNull(attachment.getId());
        editDocTypesAndCertificates(application, existedAttachments, existedCertificates, attachment, certificateNumberAndUUIDFromFile, isNewCertificate);
        if (Objects.isNull(attachment.getId())) {
            existedAttachments.add(attachment);
        } else {
            AttachedDocDTO existedAttachment = existedAttachments.stream().filter(r -> r.getId().equals(attachment.getId())).findFirst().orElse(null);
            int existedRecordIndex = existedAttachments.indexOf(existedAttachment);
            existedAttachments.set(existedRecordIndex, attachment);
        }
        String certificateNumber = null;
        String fileUUID = null;
        if (Objects.nonNull(certificateNumberAndUUIDFromFile)) {
            certificateNumber = certificateNumberAndUUIDFromFile.getFirst();
            fileUUID = certificateNumberAndUUIDFromFile.getSecond();
        }
        return new UpdateAttachmentsResultDTO(certificateNumber, fileUUID, isNewCertificate);
    }

    @Override
    public void addNewCertificateOnCreate(ApplicationDTO savedApplication, UpdateAttachmentsResultDTO updateAttachmentsResultDTO) {
        if (updateAttachmentsResultDTO.isNewCertificate()) {
            ApplicationCertificatesDTO newCertificate = new ApplicationCertificatesDTO();
            newCertificate.setCertificateNumber(updateAttachmentsResultDTO.getCertificateNumber());
            newCertificate.setUuid(updateAttachmentsResultDTO.getFileUUID());
            newCertificate.setCertificateStatus(ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code());
            Long newAttachmentId = savedApplication.getAttachments().stream().mapToLong(AttachedDocDTO::getId).max().orElse(-1);
            if (newAttachmentId != -1) {
                newCertificate.setApplicationAttachedDocId(newAttachmentId.intValue());
                applicationCertificatesService.saveCertificate(newCertificate, savedApplication);
            }

        }
    }

    @Override
    public AttachedDocDTO selectAttachment(Integer id) {
        return attachedDocService.selectById(id);
    }

    protected void deleteAttachmentInternal(Integer id) {
        AttachedDocDTO dto = selectAttachment(id);
        Optional<Integer> docflowId = ObjectUtils.isEmpty(dto.getDocflowId()) ? Optional.empty() : Optional.of(Integer.parseInt(dto.getDocflowId()));
        Doc abdocsDoc = docflowId.isPresent() ? abdocsAdminService.getDocumentById(docflowId.get()) : null;
        BadRequestValidator.validateRequest(deleteAttachmentValidator, dto, abdocsDoc);
        applicationCertificatesService.deleteCertificatesByAttachmentId(id);
        attachedDocService.delete(id);
        docflowId.ifPresent(d -> abdocsAdminService.deleteDocument(d));
    }

    private void editDocTypesAndCertificates(ApplicationDTO application, List<AttachedDocDTO> attachments, List<ApplicationCertificatesDTO> certificates, AttachedDocDTO editedAttachment, Pair<String, String> certificateNumberAndUUIDFromFile, boolean isNewCertificate) {
        if (isNewCertificate) {
            editAttachmentsDocTypes(application, attachments);
        }
        editCertificatesStatuses(certificates, editedAttachment, certificateNumberAndUUIDFromFile, isNewCertificate);
    }

    private void editAttachmentsDocTypes(ApplicationDTO application, List<AttachedDocDTO> attachments) {
        if (!CollectionUtils.isEmpty(attachments)) {
            Optional<CertificateDocTypes> cdt = CertificateDocTypes.getCertificateDocTypes(ApplicationType.selectByCode(application.getApplicationType().getId()), ApplicationSubType.selectByTypeAndSubType(application.getApplicationType().getId(), application.getApplicationSubtype().getId()));
            for (AttachedDocDTO attachment : attachments) {
                Integer dte = attachment.getDocumentType().getId();
                if (cdt.isPresent() && cdt.get().isAnyDocType(dte)) {
                    attachment.getDocumentType().setId(cdt.get().getDestroyedDocTypeId());
                }
            }
        }
    }

    private void editCertificatesStatuses(List<ApplicationCertificatesDTO> certificates, AttachedDocDTO editedAttachment, Pair<String, String> certificateNumberAndUUIDFromFile, boolean isNewCertificate) {
        if (!CollectionUtils.isEmpty(certificates)) {
            for (ApplicationCertificatesDTO certificate : certificates) {
                if (Objects.nonNull(editedAttachment.getId()) && Objects.nonNull(certificate.getApplicationAttachedDocId()) && !isNewCertificate && editedAttachment.getId().equals(certificate.getApplicationAttachedDocId())) {
                    if (Objects.nonNull(certificateNumberAndUUIDFromFile)) {
                        certificate.setCertificateNumber(certificateNumberAndUUIDFromFile.getFirst());
                        certificate.setUuid(certificateNumberAndUUIDFromFile.getSecond());
                    }
                    if (Set.of(DocTypes.CERTIFICATE_REVOKED.code(), DocTypes.OFFICIAL_NOTE_REVOKED.code(), DocTypes.VERIFICATION_LETTER_REVOKED.code()).contains(editedAttachment.getDocumentType().getId())) {
                        certificate.setCertificateStatus(ReferenceDataCode.CERTIFICATE_STATUS_DIMINISH.code());
                    }
                    continue;
                }
                if (isNewCertificate) {
                    certificate.setCertificateStatus(ReferenceDataCode.CERTIFICATE_STATUS_DESTROYED.code());
                }
            }
        }
    }

    private void initAttachmentsAndCertificates(ApplicationDTO application) {
        if (CollectionUtils.isEmpty(application.getAttachments())) {
            application.setAttachments(new ArrayList<>());
        }

        if (CollectionUtils.isEmpty(application.getCertificates())) {
            application.setCertificates(new ArrayList<>());
        }
    }

    private Pair<String, String> getCertificateNumberAndUUIDFromFile(AttachedDocDTO attachmentDocDTO) {
        List<AttachedDocAttachmentDTO> attachedDocAttachments = attachmentDocDTO.getAttachedDocAttachments();
        AttachmentDTO attachment = attachedDocAttachments.get(0).getAttachment();

        if (Objects.isNull(attachment) || !StringUtils.hasText(attachment.getBucketName())) {
            return null;
        }
        MinioFilePath minioFilePath = MinioBucketManager.selectMinioFilePath(attachment);
        FileStoreEntryDTO fileDetails = boAdminFileStoreClient.getFileDetails(minioFilePath.getBucket(), minioFilePath.getRelativePath(), minioFilePath.getFileId());
        Map<String, String> metadata = fileDetails.getAdditionalMetadata();

        if (CollectionUtils.isEmpty(metadata)) {
            return null;
        }

        String certificateNumber = metadata.get(MetadataKey.CERTIFICATE_NUMBER_KEY);
        String uuidAsString = metadata.get(MetadataKey.CERTIFICATE_UUID_KEY);

        return Pair.of(certificateNumber, uuidAsString);
    }

}
