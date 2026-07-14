package bg.duosoft.nacid.backoffice.core.be.service.common.accept_app.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.accept_app.AcceptApplicationFileService;
import bg.duosoft.nacid.backoffice.core.be.service.fo.FoAppService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AttachmentVisibility;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocTypes;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoAttachedDocMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoAttachmentMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoFileStoreEntryMapper;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioFilePath;
import bg.duosoft.nacidcoreclient.client.fileStore.AdminFileStoreClient;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.AttachedDocumentDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicEntryDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptApplicationFilesServiceImpl implements AcceptApplicationFileService {

    private final FoAppService foAppService;
    private final FoAttachedDocMapper foAttachedDocMapper;
    private final FoAttachmentMapper foAttachmentMapper;
    private final FoFileStoreEntryMapper foFileStoreEntryMapper;
    private final AdminFileStoreClient adminFileStoreClient;
    private final FileStoreService fileStoreService;

    @Override
    public List<AttachedDocDTO> processFiles(ApplicationDTO application) {
        CommonApplicationDTO foApplication = foAppService.selectFoApplication(application.getEfilingId(), application.getApplicationType().getId(), application.getApplicationSubtype().getId());
        List<AttachedDocumentDTO> foAttachments = extractFoAttachments(foApplication);
        if (CollectionUtils.isEmpty(foAttachments)) {
            return null;
        }

        List<AttachedDocDTO> resultList = new ArrayList<>();
        try {
            for (AttachedDocumentDTO foAttachment : foAttachments) {
                FileStoreEntryDTO foFile = adminFileStoreClient.getFileDetailsAndContent(foAttachment.getFile().getRootDirectory(), foAttachment.getFile().getRelativePath(), foAttachment.getFile().getFileId());
                if (Objects.nonNull(foFile)) {
                    FileStoreEntryBaseDTO boFile = fileStoreService.saveNewFile(FileConstants.FILE_GROUP_NOLIMIT, "acceptApplicationFiles", convertToBoFileStoreEntry(application, foFile));
                    AttachedDocDTO boAttachedDoc = createBoAttachedDoc(application, foAttachment, foFile, boFile.getFileId());
                    resultList.add(boAttachedDoc);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            List<AttachmentDTO> deleteList = selectFilesForDeletionOnError(resultList);
            if (!CollectionUtils.isEmpty(deleteList)) {
                for (AttachmentDTO fileToDelete : deleteList) {
                    try {
                        MinioFilePath minioFilePath = MinioBucketManager.selectMinioFilePath(fileToDelete);
                        fileStoreService.removeFile(minioFilePath.getBucket(), minioFilePath.getRelativePath(), minioFilePath.getFileId());
                    } catch (Exception ex) {
                        log.error("[MINIO] Cannot remove file! Bucket: {}, File location: {}", fileToDelete.getBucketName(), fileToDelete.getFileLocation());
                        log.error(ex.getMessage(), e);
                    }
                }
            }
            throw e;
        }

        return resultList;
    }

    @Override
    public AttachmentDTO processDocDeliveryAttachment(String foFileNameAndId, ApplicationDTO application) {
        if (!StringUtils.hasText(foFileNameAndId)) {
            return null;
        }

        DocDeliveryApplicationDTO foApplication = (DocDeliveryApplicationDTO) foAppService.selectFoApplication(application.getEfilingId(), application.getApplicationType().getId(), application.getApplicationSubtype().getId());
        DocBibliographicDetailsDTO bibliographicDetails = foApplication.getBibliographicDetails();
        if (Objects.isNull(bibliographicDetails)) {
            return null;
        }

        List<DocBibliographicEntryDetailsDTO> entries = bibliographicDetails.getEntries();
        if (CollectionUtils.isEmpty(entries)) {
            return null;
        }

        List<FileStoreEntryDTO> foFiles = entries.stream().map(DocBibliographicEntryDetailsDTO::getFile).filter(Objects::nonNull).toList();
        if (CollectionUtils.isEmpty(foFiles)) {
            return null;
        }

        FileStoreEntryDTO fileMatch = foFiles.stream().filter(a -> (a.getFileName() + a.getFileId()).equalsIgnoreCase(foFileNameAndId)).findFirst().orElse(null);
        if (Objects.isNull(fileMatch)) {
            throw new RuntimeException("Not found document delivery file: FileName + FileId: " + foFileNameAndId);
        }

        FileStoreEntryDTO foFile = adminFileStoreClient.getFileDetailsAndContent(fileMatch.getRootDirectory(), fileMatch.getRelativePath(), fileMatch.getFileId());
        if (Objects.isNull(foFile)) {
            return null;
        }

        FileStoreEntryBaseDTO boFile = fileStoreService.saveNewFile(FileConstants.FILE_GROUP_NOLIMIT, "acceptApplicationDocumentDeliveryFiles", convertToBoFileStoreEntry(application, foFile));

        AttachmentDTO boAttachment = foAttachmentMapper.toBackofficeObject(foFile);
        boAttachment.setBucketName(MinioBucketManager.selectBucketName(application));
        boAttachment.setFileLocation(MinioBucketManager.buildAppsRelativePath(application) + "/" + boFile.getFileId());
        return boAttachment;
    }


    public List<AttachedDocumentDTO> extractFoAttachments(CommonApplicationDTO foApplication) {
        if (Objects.isNull(foApplication)) {
            throw new RuntimeException("Cannot extract front-office application files, because object is empty !");
        }

        DocumentDetailsDTO foDocumentDetails = foApplication.getDocumentDetails();
        if (Objects.isNull(foDocumentDetails)) {
            return null;
        }

        List<AttachedDocumentDTO> foAttachments = foDocumentDetails.getAttachments();
        if (CollectionUtils.isEmpty(foAttachments)) {
            return null;
        }

        return foAttachments.stream().filter(a -> Objects.nonNull(a.getFile())).collect(Collectors.toList());
    }

    public AttachedDocDTO createBoAttachedDoc(ApplicationDTO application, AttachedDocumentDTO foAttachment, FileStoreEntryDTO foFile, String newFileId) {
        AttachmentDTO boAttachment = foAttachmentMapper.toBackofficeObject(foFile);
        boAttachment.setBucketName(MinioBucketManager.selectBucketName(application));
        boAttachment.setFileLocation(MinioBucketManager.buildAppsRelativePath(application) + "/" + newFileId);

        AttachedDocAttachmentDTO attachedDocAttachmentDTO = new AttachedDocAttachmentDTO();
        attachedDocAttachmentDTO.setAttachmentVisibility(new ReferenceDataDTO(ReferenceDataDomain.ATTACHMENT_VISIBILITY.domain(), AttachmentVisibility.INTERNAL.code()));
        attachedDocAttachmentDTO.setAttachment(boAttachment);

        AttachedDocDTO boAttachedDoc = foAttachedDocMapper.toBackofficeObject(foAttachment);
        boAttachedDoc.setAttachedDocAttachments(Collections.singletonList(attachedDocAttachmentDTO));

        if (Objects.isNull(boAttachedDoc.getDocumentType())) {
            boAttachedDoc.setDocumentType(new DocumentTypeDTO(DocTypes.OTHER_DOCS.code()));//Default doc type
        }

        return boAttachedDoc;
    }

    public List<AttachmentDTO> selectFilesForDeletionOnError(List<AttachedDocDTO> attachedDocs) {
        if (CollectionUtils.isEmpty(attachedDocs)) {
            return null;
        }

        return attachedDocs.stream()
                .filter(attachedDocDTO -> !CollectionUtils.isEmpty(attachedDocDTO.getAttachedDocAttachments()))
                .flatMap(attachedDocDTO -> attachedDocDTO.getAttachedDocAttachments().stream())
                .filter(Objects::nonNull)
                .map(AttachedDocAttachmentDTO::getAttachment)
                .filter(Objects::nonNull)
                .filter(attachment -> StringUtils.hasText(attachment.getFileLocation()))
                .collect(Collectors.toList());
    }

    public bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO convertToBoFileStoreEntry(ApplicationDTO application, FileStoreEntryDTO foFile) {
        try {
            FileStoreEntryDTO clone = (FileStoreEntryDTO) foFile.clone();
            clone.setFileId(null);
            clone.setRelativePath(MinioBucketManager.buildAppsRelativePath(application));
            clone.setRootDirectory(MinioBucketManager.selectBucketName(application));
            return foFileStoreEntryMapper.toBackofficeObject(clone);
        } catch (CloneNotSupportedException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
