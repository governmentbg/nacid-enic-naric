package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

public interface PersonIdentifierValidator<T> extends Validator<T>, CivilIdValidator {

    default void validatePersonalId(List<ValidationError> errors, boolean isRequired, String personalId, CivilIdType civilIdType, String pointer) {
        if (isRequired) {
            rejectIfEmptyString(errors, personalId, pointer, "validation.field.required");
        }
        validateCivilId(errors,civilIdType,personalId,pointer);
    }

    default void validateCivilIdType(List<ValidationError> errors, CivilIdType civilIdType, String pointer) {
        CivilIdType identifierType = Stream.of(CivilIdType.values()).filter(i -> i == civilIdType).findFirst().orElse(null);
        rejectIfEmpty(errors, identifierType, pointer, "validation.field.required");
    }
}
