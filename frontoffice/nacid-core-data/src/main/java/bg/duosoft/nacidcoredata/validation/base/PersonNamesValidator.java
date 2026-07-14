package bg.duosoft.nacidcoredata.validation.base;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.util.List;

public interface PersonNamesValidator<T> extends Validator<T> {
    default void validateFirstName(List<ValidationError> errors, String firstName, String pointer) {
        rejectIfEmptyString(errors, firstName, pointer, "validation.field.required");
        if (StringUtils.hasText(firstName)) {
            rejectIfStringLengthBigger(errors, firstName, 100, pointer);
            rejectIfNotMatchRegex(errors, firstName, RegexUtils.NAME_VALIDATION_REGEX, pointer, "validation.field.invalid");
        }
    }

    default void validateMiddleName(List<ValidationError> errors, String middleName, CountryDTO citizenship, CountryDTO birthCountry, String pointer) {
        if (citizenship != null && birthCountry != null && DefaultValue.BG_COUNTRY_CODE.equals(citizenship.getId()) && DefaultValue.BG_COUNTRY_CODE.equals(birthCountry.getId())) {
            rejectIfEmptyString(errors, middleName, pointer, "validation.field.required");
        }

        if (StringUtils.hasText(middleName)) {
            rejectIfStringLengthBigger(errors, middleName, 100, pointer);
            rejectIfNotMatchRegex(errors, middleName, RegexUtils.NAME_VALIDATION_REGEX, pointer, "validation.field.invalid");
        }
    }

    default void validateLastName(List<ValidationError> errors, String lastName, PersonalIdentifierType identifierType, CountryDTO citizenship, String pointer) {
        if (citizenship != null && (DefaultValue.BG_COUNTRY_CODE.equals(citizenship.getId()) || identifierType == PersonalIdentifierType.NATIONAL_ID)) {
            rejectIfEmptyString(errors, lastName, pointer, "validation.field.required");
        }

        if (StringUtils.hasText(lastName)) {
            rejectIfStringLengthBigger(errors, lastName, 100, pointer);
            rejectIfNotMatchRegex(errors, lastName, RegexUtils.NAME_VALIDATION_REGEX, pointer, "validation.field.invalid");
        }
    }
}
