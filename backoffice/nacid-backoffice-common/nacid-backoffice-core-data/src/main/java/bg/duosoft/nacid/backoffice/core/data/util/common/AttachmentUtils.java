package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class AttachmentUtils {

    public static void overrideAttachedDocsData(List<AttachedDocDTO> attachedDocs) {
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            for (AttachedDocDTO attachedDoc : attachedDocs) {
                ReferenceDataUtils.setDefaultDomain(attachedDoc.getCopyType(), ReferenceDataDomain.COPY_TYPE);
                ReferenceDataUtils.setDefaultDomain(attachedDoc.getDocCategory(), ReferenceDataDomain.DOC_CATEGORY);

                //TODO This has to be tested, because it is transferred from the previous implementation and it was written for the scanned files only !!!
                List<AttachedDocAttachmentDTO> attachedDocAttachments = attachedDoc.getAttachedDocAttachments();
                if (!CollectionUtils.isEmpty(attachedDocAttachments)) {
                    attachedDocAttachments.removeIf(attachmentIsEmptyOrWithoutMinioData);
                    if (CollectionUtils.isEmpty(attachedDocAttachments)) {
                        attachedDoc.setAttachedDocAttachments(null);
                    }
                }
            }
        }
    }

    private static Predicate<AttachedDocAttachmentDTO> attachmentIsEmptyOrWithoutMinioData = attachedDocAttachmentDTO -> {
        if (Objects.isNull(attachedDocAttachmentDTO)) {
            return true;
        }

        AttachmentDTO attachment = attachedDocAttachmentDTO.getAttachment();
        if (Objects.isNull(attachment)) {
            return true;
        }

        if (!StringUtils.hasText(attachment.getBucketName()) || !StringUtils.hasText(attachment.getFileLocation())) {
            return true;
        }

        return false;
    };

    public static void sortAttachedDocsData(List<AttachedDocDTO> attachedDocs) {
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            attachedDocs.sort(Comparator.comparing(AttachedDocDTO::getId).reversed());
        }
    }

    public static void reverseUIAttachedDocs(List<AttachedDocDTO> attachedDocs) {
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            Collections.reverse(attachedDocs);
        }
    }
}
