package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.StringUtils;

import java.util.List;

public interface CivilIdValidator extends DefaultValidation {
    default void validateCivilId(List<ValidationError> errors, CivilIdType civilIdType, String civilId, String pointer) {
        if (StringUtils.hasText(civilId)) {
            if (civilIdType == null) {
                reject(errors, pointer, "validation.field.required");
            } else if (civilIdType == CivilIdType.EGN) {
                rejectIfFalse(errors, CivilIdUtils.validateEGN(civilId), pointer, "validation.field.invalid");
            } else if (civilIdType == CivilIdType.LNCH) {
                rejectIfFalse(errors, CivilIdUtils.validateLNCH(civilId), pointer, "validation.field.invalid");
            } else if (civilIdType == CivilIdType.FOREIGN_COUNTRY_ID) {
                rejectIfTrue(errors, civilId.length() > MAX_INPUT_LENGTH_XS, pointer, "validation.documentId.charCount.invalid");
            }
        }
    }
}
