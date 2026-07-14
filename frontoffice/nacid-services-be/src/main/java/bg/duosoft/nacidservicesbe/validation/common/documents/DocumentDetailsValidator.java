package bg.duosoft.nacidservicesbe.validation.common.documents;

import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.FileValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.06.2022
 * Time: 14:54
 */
@Component
@RequiredArgsConstructor
public class DocumentDetailsValidator implements Validator<DocumentDetailsDTO> {

    private final FileValidator fileValidator;

    @Override
    public List<ValidationError> validate(DocumentDetailsDTO documentDetails, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if(documentDetails.getAttachments() != null && documentDetails.getAttachments().size() > 0 &&
                documentDetails.getAttachments().stream().anyMatch(
                        doc -> (!StringUtils.hasText(doc.getDescription()) && (doc.getAttachmentType() == null || doc.getAttachmentType().getId() == null)) || !fileValidator.fileIsValid(doc.getFile())
                )){
            errors.add(ValidationError.builder().pointer("attachments").message(ValidationMessageCodes.BAD_VALUE_IN_ARRAY_CODE).build());
        }
        return errors;
    }
}
