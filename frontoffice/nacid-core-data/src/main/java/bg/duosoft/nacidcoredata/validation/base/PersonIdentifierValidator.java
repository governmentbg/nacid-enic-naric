package bg.duosoft.nacidcoredata.validation.base;

import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonIdentifierValidator<T> extends Validator<T> {

    default void validatePersonalId(List<ValidationError> errors, boolean isRequired, String personalId, PersonalIdentifierType personalIdType, String pointer) {
        if (isRequired) {
            rejectIfEmptyString(errors, personalId, pointer, "validation.field.required");
        }

        if (StringUtils.hasText(personalId)) {
            if (personalIdType == PersonalIdentifierType.NATIONAL_ID) {
                rejectIfFalse(errors, CivilIdUtils.validateEGN(personalId), pointer, "validation.field.invalid");
            } else if (personalIdType == PersonalIdentifierType.NATIONAL_FOREIGNER_ID) {
                rejectIfFalse(errors, CivilIdUtils.validateLNCH(personalId), pointer, "validation.field.invalid");
            } else if (personalIdType == PersonalIdentifierType.DOCUMENT_ID) {
                rejectIfStringLengthBigger(errors, personalId, 50, pointer);
            }
        }
    }

    default void validatePersonalIdType(List<ValidationError> errors, PersonalIdentifierType personalIdType, String pointer) {
        PersonalIdentifierType identifierType = Stream.of(PersonalIdentifierType.values()).filter(i -> i == personalIdType).findFirst().orElse(null);
        rejectIfEmpty(errors, identifierType, pointer, "validation.field.required");
    }

    default void validateBirthDateForNationalId(List<ValidationError> errors, PersonalIdentifierType personalIdType, String personalId, LocalDate birthDate, String pointer) {
        if(personalIdType != null && personalIdType.equals(PersonalIdentifierType.NATIONAL_ID) &&
                personalId != null && CivilIdUtils.validateEGN(personalId) &&
                birthDate != null){
            LocalDate extractedDate = CivilIdUtils.getBirthDate(personalId);
            rejectIfFalse(errors, birthDate.equals(extractedDate), pointer, "validation.field.dateDoesNotMatchIdDate");
        }
    }
}
