package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.client.client.common.abdocs.AbdocsFileTransferClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsConfigUtils;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsAutoFileTransferService;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AbdocsAutoFileTransferServiceImpl implements AbdocsAutoFileTransferService {

    @Lazy
    @Autowired
    private AbdocsFileTransferClient abdocsFileTransferClient;

    @Lazy
    @Autowired
    private AbdocsUrlBuilder abdocsUrlBuilder;

    @Autowired
    private BaseApplicationService baseApplicationService;


    @Override
    public boolean transferFiles(Integer applicationId, List<AttachedDocDTO> attachedDocs) {
        boolean allFilesTransferred = true;
        if (CollectionUtils.isEmpty(attachedDocs)) {
            return false;
        }

        for (AttachedDocDTO attachedDoc : attachedDocs) {
            boolean transferredFile = transferFile(applicationId, attachedDoc);
            if (!transferredFile){
                allFilesTransferred = false;
            }
        }

        return allFilesTransferred;
    }

    @Override
    public boolean transferFile(Integer applicationId, AttachedDocDTO attachedDoc) {
        boolean transferredFile = true;
        if (Objects.isNull(applicationId) || Objects.isNull(attachedDoc)) {
           return false;
        }

        if (StringUtils.hasText(attachedDoc.getDocflowId())) {
            return false;
        }
        ApplicationDTO applicationDTO = baseApplicationService.selectApplicationById(applicationId);
        if (Objects.isNull(applicationDTO)) {
            return false;
        }

        String docflowId = null;
        try {
            DocumentTypeDTO documentType = attachedDoc.getDocumentType();
            if (AbdocsConfigUtils.shouldTransferFile(documentType, applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId())) {
                AttachedDocDTO transferredDoc = abdocsFileTransferClient.abdocsTransferAttachedDocFiles(applicationId, attachedDoc.getId());
                if (Objects.nonNull(transferredDoc)) {
                    attachedDoc.setDocflowId(transferredDoc.getDocflowId());
                    docflowId = transferredDoc.getDocflowId();
                }
            }
        } catch (Exception e) {
            log.error("[Auto Abdocs File Transfer] Cannot transfer file ! ApplicationId: {}, AttachedDocId: {}", applicationId, attachedDoc.getId());
            log.error(e.getMessage(), e);
            transferredFile = false;
        }

        setViewDocumentUrl(attachedDoc, docflowId);
        return transferredFile;
    }

    @Override
    public boolean transferApplicationFiles(Integer applicationId) {
        ApplicationDTO applicationDTO = baseApplicationService.selectApplicationById(applicationId);
        if (Objects.isNull(applicationDTO)) {
            return false;
        }

        List<AttachedDocDTO> attachments = applicationDTO.getAttachments();
        if (CollectionUtils.isEmpty(attachments)) {
            return false;
        }

        List<AttachedDocDTO> forTransfer = attachments.stream()
                .filter(a -> AbdocsConfigUtils.shouldTransferFile(a.getDocumentType(), applicationDTO.getApplicationType().getId(), applicationDTO.getApplicationSubtype().getId()))
                .filter(a -> Objects.isNull(a.getDocflowId()))
                .toList();

        if (CollectionUtils.isEmpty(forTransfer)) {
            return false;
        }

       return transferFiles(applicationId, forTransfer);
    }

    private void setViewDocumentUrl(AttachedDocDTO attachedDoc, String docflowId) {
        if (StringUtils.hasText(docflowId)) {
            try {
                attachedDoc.setAbdocsViewDocumentUrl(abdocsUrlBuilder.viewDocWithAuth(Integer.valueOf(docflowId)));
            } catch (Exception e) {
                log.warn("[Auto Abdocs File Transfer] Cannot build view document url ! DocflowId: {}, AttachedDocId: {}", docflowId, attachedDoc.getId());
                log.warn(e.getMessage(), e);
            }
        }
    }

}
