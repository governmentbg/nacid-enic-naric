package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

//TODO TEST it when UI components are ready and change it if necessary !
public interface AttachedDocumentValidator extends DefaultValidation {

    default void validateAttachedDocuments(List<ValidationError> errors, List<AttachedDocDTO> attachedDocs) {
        if (!CollectionUtils.isEmpty(attachedDocs)) {
            for (int i = 0; i < attachedDocs.size(); i++) {
                AttachedDocDTO attachedDoc = attachedDocs.get(i);
                validateAttachments(errors, attachedDoc, "attachedDocs." + i + ".attachedAttachments");
                validateDocumentType(errors, attachedDoc, "attachedDocs." + i + ".documentTypeId");
                validateDocumentCategory(errors, attachedDoc, "attachedDocs." + i + ".docCategoryId");
            }
        }
    }

    default void validateAttachedDocument(List<ValidationError> errors, AttachedDocDTO attachedDoc) {
        validateAttachments(errors, attachedDoc, "attachedDocAttachments");
        validateDocumentType(errors, attachedDoc, "documentType.id");
        validateDocumentCategory(errors, attachedDoc, "docCategory.id");
    }

    private void validateDocumentType(List<ValidationError> errors, AttachedDocDTO attachedDoc, String pointer) {
        if (Objects.nonNull(attachedDoc)) {
            rejectIfTrue(errors, Objects.isNull(attachedDoc.getDocumentType()) || Objects.isNull(attachedDoc.getDocumentType().getId()), pointer, "validation.field.required");
        }
    }

    private void validateDocumentCategory(List<ValidationError> errors, AttachedDocDTO attachedDoc, String pointer) {
        if (Objects.nonNull(attachedDoc)) {
            rejectIfTrue(errors, Objects.isNull(attachedDoc.getDocCategory()) || Objects.isNull(attachedDoc.getDocCategory().getId()), pointer, "validation.field.required");
        }
    }

    private void validateAttachments(List<ValidationError> errors, AttachedDocDTO attachedDoc, String parentPointer) {
        List<AttachedDocAttachmentDTO> attachedDocAttachments = attachedDoc.getAttachedDocAttachments();

        rejectIfEmptyCollection(errors, attachedDocAttachments, parentPointer, "attachedDocAttachments.empty");
        if (!CollectionUtils.isEmpty(attachedDocAttachments)) {
            for (int i = 0; i < attachedDocAttachments.size(); i++) {
                AttachedDocAttachmentDTO attachedDocAttachmentDTO = attachedDocAttachments.get(i);

                String pointer = parentPointer.concat(".").concat(String.valueOf(i)).concat("attachment");
                validateAttachment(errors, pointer, attachedDocAttachmentDTO);
            }
        }
    }

    private void validateAttachment(List<ValidationError> errors, String pointer, AttachedDocAttachmentDTO attachedDocAttachmentDTO) {
        if (Objects.nonNull(attachedDocAttachmentDTO)) {
            rejectIfTrue(errors, Objects.isNull(attachedDocAttachmentDTO.getAttachment()) || !StringUtils.hasText(attachedDocAttachmentDTO.getAttachment().getBucketName()) || !StringUtils.hasText(attachedDocAttachmentDTO.getAttachment().getFileLocation()), pointer, "validation.field.required");
        }
    }
}
