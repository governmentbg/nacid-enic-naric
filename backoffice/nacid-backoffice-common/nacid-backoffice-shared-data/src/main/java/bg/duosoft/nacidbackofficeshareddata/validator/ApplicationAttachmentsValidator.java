package bg.duosoft.nacidbackofficeshareddata.validator;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype.DocumentTypeClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocAttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAppStatusDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ApplicationAttachmentsValidator implements Validator<ApplicationDTO> {
    @Autowired
    @Lazy
    private DocumentTypeClient documentTypeClient;

    //TODO N22-393 This has to be tested !
    @Override
    public List<ValidationError> validate(ApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        List<AttachedDocDTO> attachments = obj.getAttachments();


        if (!CollectionUtils.isEmpty(attachments)) {
            attachments.forEach(attachment -> {
                DocumentTypeDTO documentType = attachment.getDocumentType();
                rejectIfTrue(errors, Objects.isNull(documentType) || Objects.isNull(documentType.getId()), "documentType.id", "validation.field.required");
                validateAttachmentStatuses(obj, errors, attachment.getId(), documentType);


                //TODO N22-393 This has to be tested !
                List<AttachedDocAttachmentDTO> attachedDocAttachments = attachment.getAttachedDocAttachments();
                rejectIfTrue(errors, CollectionUtils.isEmpty(attachedDocAttachments), "attachedDocAttachments", "attachedDocAttachments.empty");
                if (!CollectionUtils.isEmpty(attachedDocAttachments)) {
                    for (int i = 0; i < attachedDocAttachments.size(); i++) {
                        AttachedDocAttachmentDTO attachedDocAttachmentDTO = attachedDocAttachments.get(i);
                        if (Objects.nonNull(attachedDocAttachmentDTO)) {
                            AttachmentDTO attachmentDTO = attachedDocAttachmentDTO.getAttachment();
                            rejectIfTrue(errors, Objects.isNull(attachmentDTO) || Objects.isNull(attachmentDTO.getFileName()), Strings.concat("attachedDocAttachments.", i + ".attachment"), "validation.field.required");
                        }
                    }
                }
            });
        }
        return errors;
    }


    public void validateAttachmentStatuses(ApplicationDTO obj, List<ValidationError> errors, Integer attachmentId, DocumentTypeDTO documentType) {
        if (Objects.isNull(attachmentId) && Objects.nonNull(documentType) && Objects.nonNull(documentType.getId())) {
            DocumentTypeDTO existedDocumentType = documentTypeClient.selectById(String.valueOf(documentType.getId()));
            if (!CollectionUtils.isEmpty(existedDocumentType.getStatuses())) {
                DocumentTypeAppStatusDetailDTO statusConfiguration = existedDocumentType.getStatuses().stream().filter(r -> r.getStatus().getId().equals(obj.getStatus().getId())).findFirst().orElse(null);
                rejectIfTrue(errors, Objects.isNull(statusConfiguration), "documentType.id", "m.documentType.invalid");
            }
        }
    }
}
