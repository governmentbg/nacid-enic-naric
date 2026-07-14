package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsService;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsFileTransferService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachmentsService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AttachmentVisibility;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsConfigUtils;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioFilePath;
import bg.duosoft.nacid.backoffice.libserv.client.client.abdocs.AbdocsMissingLibservDocumentClient;
import bg.duosoft.nacid.backoffice.regprof.client.client.abdocs.AbdocsMissingRegprofDocumentClient;
import bg.duosoft.nacid.backoffice.rudi.client.client.abdocs.AbdocsMissingRudiDocumentClient;
import bg.duosoft.nacidbackofficeshareddata.converter.AbdocsDocActionConverter;
import bg.duosoft.nacidbackofficeshareddata.converter.AbdocsFileTransferConverter;
import bg.duosoft.nacidbackofficeshareddata.service.AttachedDocService;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AbdocsFileTransferServiceImpl implements AbdocsFileTransferService {

    private final ApplicationsService applicationsService;
    private final FileStoreService fileStoreService;
    private final AttachedDocService attachedDocService;
    private final AbdocsService abdocsService;
    private final AbdocsAdminService abdocsAdminService;
    private final AbdocsFileTransferConverter abdocsFileTransferConverter;
    private final ApplicationAttachmentsService applicationAttachmentsService;
    private final AbdocsMissingRegprofDocumentClient abdocsMissingRegprofDocumentClient;
    private final AbdocsMissingRudiDocumentClient abdocsMissingRudiDocumentClient;
    private final AbdocsMissingLibservDocumentClient abdocsMissingLibservDocumentClient;
    private final AbdocsDocActionConverter abdocsDocActionConverter;

    @Override
    public AttachedDocDTO transferApplicationAttachment(Integer applicationId, Integer attachedDocId) {
        ApplicationDTO application = applicationsService.getApplicationById(applicationId);
        if (Objects.isNull(application)) {
            throwError("[Abdocs File Transfer] Application is empty ! App ID: " + applicationId);
        }

        AttachedDocDTO attachedDocDTO = attachedDocService.selectByIdAndApplicationId(attachedDocId, applicationId);
        if (Objects.isNull(attachedDocDTO)) {
            throwError("[Abdocs File Transfer] Cannot find attached document with id = " + attachedDocId + "! App ID: " + applicationId);
        }

        String existingDocflowId = attachedDocDTO.getDocflowId();
        if (StringUtils.hasText(existingDocflowId)) {
            throwError("[Abdocs File Transfer] Cannot transfer attachment, because it has been already transferred ! Attached document id: " + attachedDocId + "! App ID: " + applicationId);
        }

        AttachedDocDTO transferredAttachedDoc = transferAttachedDoc(applicationId, attachedDocId, application, attachedDocDTO);
        insertDocAction(transferredAttachedDoc);
        return transferredAttachedDoc;
    }

    private void insertDocAction(AttachedDocDTO transferredAttachedDoc) {
        Integer id = transferredAttachedDoc.getId();
        DocumentTypeDTO documentType = transferredAttachedDoc.getDocumentType();
        String docflowIdString = transferredAttachedDoc.getDocflowId();
        if (AbdocsConfigUtils.shouldCreateDocAction(documentType)) {
            try {
                DocActionRequest docActionRequest = abdocsDocActionConverter.createDocActionRequest(Integer.valueOf(docflowIdString), documentType);
                abdocsAdminService.insertDocAction(docActionRequest);
                log.info("[Abdocs File Transfer] Doc action has been inserted successfully ! DocflowId: {}, AttachedDocId: {} ", docflowIdString, id);
            } catch (Exception e) {
                log.error("[Abdocs File Transfer] Cannot insert doc action ! DocflowID: " + docflowIdString);
                log.error(e.getMessage(), e);
            }
        }
    }

    private AttachedDocDTO transferAttachedDoc(Integer applicationId, Integer attachedDocId, ApplicationDTO application, AttachedDocDTO attachedDocDTO) {
        addMissingAbdocsDocument(applicationId, application);

        DocCreation docCreation = abdocsFileTransferConverter.convertFileDocumentObject(application, attachedDocDTO);
        Doc document = abdocsService.createDocument(docCreation);

        try {
            List<AttachedDocAttachmentDTO> attachedDocAttachments = attachedDocDTO.getAttachedDocAttachments();
            if (!CollectionUtils.isEmpty(attachedDocAttachments)) {
                for (AttachedDocAttachmentDTO attachedDocAttachment : attachedDocAttachments) {
                    Attachments attachment = convertToAbdocsAttachment(attachedDocAttachment.getAttachment());
                    if (Objects.nonNull(attachment)) {
                        uploadAttchment(attachment, document, getAbdocsDocFileVisibility(attachedDocAttachment));
                    }
                }
            }

            return applicationAttachmentsService.updateDocflowId(attachedDocId, String.valueOf(document.getDocId()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            Integer docId = document.getDocId();
            try {
                abdocsAdminService.deleteDocument(docId);
            } catch (Exception ex) {
                log.error("[Abdocs File Transfer]  Cannot delete document with id " + docId);
                log.error(e.getMessage(), e);
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void addMissingAbdocsDocument(Integer applicationId, ApplicationDTO application) {
        try {
            ApplicationType applicationType = ApplicationType.selectByCode(application.getApplicationType().getId());
            switch (applicationType) {
                case REGPROF -> abdocsMissingRegprofDocumentClient.addMissingAbdocsDocument(applicationId);
                case RUDI -> abdocsMissingRudiDocumentClient.addMissingAbdocsDocument(applicationId);
                case LIBSERV -> abdocsMissingLibservDocumentClient.addMissingAbdocsDocument(applicationId);
            }
        } catch (Exception e) {
            log.error("[Abdocs File Transfer - Missing doc] - Error occured during execution of the 'Add missing abdocs document' process ! Application ID: " + applicationId);
            log.error(e.getMessage(), e);
        }
    }

    private static DocFileVisibility getAbdocsDocFileVisibility(AttachedDocAttachmentDTO attachedDocAttachment) {
        ReferenceDataDTO attachmentVisibilityRefData = attachedDocAttachment.getAttachmentVisibility();
        if (!ReferenceDataUtils.hasRefDataId(attachmentVisibilityRefData)) {
            log.error("[Abdocs File Transfer] Attachment visibility is empty !");
            throw new RuntimeException("[Abdocs File Transfer] Attachment visibility is empty !");
        }

        DocFileVisibility abdocsFileVisibility = DocFileVisibility.PublicAttachedFile;
        AttachmentVisibility attachmentVisibility = AttachmentVisibility.selectByCode(attachmentVisibilityRefData.getId());
        if (attachmentVisibility == AttachmentVisibility.INTERNAL) {
            abdocsFileVisibility = DocFileVisibility.PrivateAttachedFile;
        }

        return abdocsFileVisibility;
    }

    private Attachments convertToAbdocsAttachment(AttachmentDTO attachment) {
        if (Objects.isNull(attachment)) {
            return null;
        }

        MinioFilePath minioFilePath = MinioBucketManager.selectMinioFilePath(attachment);
        FileStoreEntryBaseDTO file = fileStoreService.getFileStoreEntryDetailsAndContent(minioFilePath.getBucket(), minioFilePath.getRelativePath(), minioFilePath.getFileId());
        if (Objects.isNull(file)) {
            return null;
        }

        return new Attachments(file.getContent(), attachment.getContentType(), attachment.getFileName());
    }

    private void uploadAttchment(Attachments attachment, Doc document, DocFileVisibility docFileVisibility) {
        if (Objects.nonNull(attachment)) {
            abdocsService.uploadFile(document.getDocId(), attachment.getContent(), attachment.getName(), null, false, docFileVisibility);
        }
    }

    private void throwError(String message) {
        log.error(message);
        throw new RuntimeException(message);
    }


}
