package bg.duosoft.nacidbackofficeshareddata.validator;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocStatus;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * User: ggeorgiev
 * Date: 13.11.2025
 * Time: 14:04
 */
@Component
@RequiredArgsConstructor
public class DeleteAttachmentValidator implements Validator<AttachedDocDTO> {
    @Override
    public List<ValidationError> validate(AttachedDocDTO obj, Object... args) {
        List<ValidationError> errors = initErrorList();
        if (!ObjectUtils.isEmpty(obj.getDocflowId())) {
            try {
                Doc doc = (Doc) args[0];
                rejectIfTrue(errors, Set.of(DocStatus.Finished, DocStatus.Archived).contains(doc.getDocStatus()), "attachments", "delete.attachments.abdocs.document.status.error");
            } catch (Exception e) {
                reject(errors, "attachments", "delete.attachments.abdocs.error");
            }
        }
        return errors;
    }
}
