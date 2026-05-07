package bg.duosoft.nacidservicesbe.validation.common.person;

import bg.duosoft.nacidcoredata.enums.ForeignIdentifierType;
import bg.duosoft.nacidcoredata.validation.base.PersonIdentifierValidator;
import bg.duosoft.nacidcoredata.validation.base.PersonNamesValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.person.*;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.base.EikValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.09.2022
 * Time: 11:28
 */
@Component
public interface PersonsValidator<T> extends PersonNamesValidator<T>, PersonIdentifierValidator<T>, EikValidator<T> {

    default void validateNaturalPersonNames(List<ValidationError> errors, NaturalPersonNamesDTO names, PersonalIdentifierType idType, CountryDTO citizenship, CountryDTO birthCountry, String basePointer) {
        validateFirstName(errors, names.getFirstName(), basePointer+".firstName");
        if(citizenship != null && birthCountry != null){
            validateMiddleName(errors, names.getMiddleName(), citizenship, birthCountry, basePointer+".middleName");
        }
        if(citizenship != null && idType != null){
            validateLastName(errors, names.getLastName(), idType, citizenship, basePointer+".lastName");
        }
    }

    default void validateNaturalPerson(List<ValidationError> errors, NaturalPersonDTO naturalPerson, String basePointer, Boolean validateBirthPlaceAndCitizenship, Boolean validateBirthDate) {
        if(naturalPerson == null){
            errors.add(ValidationError.builder().pointer(basePointer).message(ValidationMessageCodes.REQUIRED_CODE).build());
        } else {
            validateNaturalPersonNames(errors, naturalPerson, naturalPerson.getPersonalIdType(), naturalPerson.getCitizenship(), naturalPerson.getBirthCountry(), basePointer);

            if(validateBirthPlaceAndCitizenship) {
                if (naturalPerson.getBirthCountry() == null) {
                    rejectIfEmpty(errors, naturalPerson.getBirthCountry(), basePointer + ".birthCountry.id", ValidationMessageCodes.REQUIRED_CODE);
                } else {
                    rejectIfEmptyString(errors, naturalPerson.getBirthCountry().getId(), basePointer + ".birthCountry.id", ValidationMessageCodes.REQUIRED_CODE);
                    if (naturalPerson.getBirthCountry().getId() != null && naturalPerson.getBirthCountry().getId().equals(DefaultValue.BG_COUNTRY_CODE)) {
                        rejectIfEmptyString(errors, naturalPerson.getBirthSettlement() != null ? naturalPerson.getBirthSettlement().getId() : null, basePointer + ".birthSettlement.id", ValidationMessageCodes.REQUIRED_CODE);
                    } else {
                        rejectIfEmptyString(errors, naturalPerson.getBirthPlace(), basePointer + ".birthPlace", ValidationMessageCodes.REQUIRED_CODE);
                        rejectIfStringLengthBigger(errors, naturalPerson.getBirthPlace(), 30, basePointer + ".birthPlace");
                    }
                }

                if(naturalPerson.getCitizenship() == null){
                    rejectIfEmpty(errors, naturalPerson.getCitizenship(), basePointer + ".citizenship.id", ValidationMessageCodes.REQUIRED_CODE);
                } else {
                    rejectIfEmptyString(errors, naturalPerson.getCitizenship().getId(), basePointer + ".citizenship.id", ValidationMessageCodes.REQUIRED_CODE);
                }
            }

            validatePersonalId(errors, naturalPerson, basePointer);

            rejectIfNotMatchRegex(errors, naturalPerson.getEmail(), RegexUtils.EMAIL_REGEX, basePointer+".email", ValidationMessageCodes.INVALID_CODE);
            rejectIfStringLengthBigger(errors, naturalPerson.getEmail(), 100, basePointer + ".email");

            if(validateBirthDate) {
                rejectIfEmpty(errors, naturalPerson.getDateOfBirth(), basePointer + ".dateOfBirth", ValidationMessageCodes.REQUIRED_CODE);
            }
            validateBirthDateForNationalId(errors, naturalPerson.getPersonalIdType(), naturalPerson.getPersonalId(), naturalPerson.getDateOfBirth(), basePointer+".dateOfBirth");

            rejectIfStringLengthBigger(errors, naturalPerson.getTitle(), 150, basePointer + ".title");
        }
    }

    default void validatePersonalId(List<ValidationError> errors, WithPersonalIdentifier withPersonalIdentifier, String basePointer){
        validatePersonalIdType(errors, withPersonalIdentifier.getPersonalIdType(), basePointer+".personalIdType");
        if(withPersonalIdentifier.getPersonalIdType() != null){
            Boolean isDocumentIdType = withPersonalIdentifier.getPersonalIdType().equals(PersonalIdentifierType.DOCUMENT_ID);
            boolean isPersonalNacidId = isDocumentIdType && ForeignIdentifierType.OFFICIALLY_GENERATED_BY_NACID.getCode().equals(withPersonalIdentifier.getForeignerIdentifierKind().getId());

            if(!isDocumentIdType || (isDocumentIdType && !isPersonalNacidId)) {
                validatePersonalId(errors, true, withPersonalIdentifier.getPersonalId(), withPersonalIdentifier.getPersonalIdType(), basePointer + ".personalId");
            }

            if(isDocumentIdType){
                if(!isPersonalNacidId) {
                    rejectIfEmptyString(errors, withPersonalIdentifier.getForeignerIdentifierCountry() != null ? withPersonalIdentifier.getForeignerIdentifierCountry().getId() : null, basePointer + ".foreignerIdentifierCountry.id", ValidationMessageCodes.REQUIRED_CODE);
                }
                rejectIfEmptyString(errors, withPersonalIdentifier.getForeignerIdentifierKind() != null? withPersonalIdentifier.getForeignerIdentifierKind().getId(): null, basePointer+".foreignerIdentifierKind.id", ValidationMessageCodes.REQUIRED_CODE);
            }
        }
    }

    default void validateCompany(List<ValidationError> errors, CompanyDTO company, String basePointer){
        if(company == null){
            errors.add(ValidationError.builder().pointer(basePointer).message(ValidationMessageCodes.REQUIRED_CODE).build());
        } else {
            rejectIfEmptyString(errors, company.getCompanyIdentifier(), basePointer+".companyIdentifier", ValidationMessageCodes.REQUIRED_CODE);
            validateEIK(errors, company.getCompanyIdentifier(), basePointer+".companyIdentifier");
            rejectIfEmptyString(errors, company.getCompanyName(), basePointer+".companyName", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfStringLengthBigger(errors, company.getCompanyIdentifier(), 255, basePointer+".companyName");
            rejectIfEmptyString(errors, company.getCompanySettlement() != null ? company.getCompanySettlement().getId(): null, basePointer+".companySettlement.id", ValidationMessageCodes.REQUIRED_CODE);
        }
    }

    default void validateUniversity(List<ValidationError> errors, UniversityDTO university, String basePointer){
        if(university == null){
            errors.add(ValidationError.builder().pointer(basePointer).message(ValidationMessageCodes.REQUIRED_CODE).build());
        } else {
            rejectIfEmptyString(errors, university.getUniversityIdentifier(), basePointer+".universityIdentifier", ValidationMessageCodes.REQUIRED_CODE);
            validateEIK(errors, university.getUniversityIdentifier(), basePointer+".universityIdentifier");
            rejectIfEmptyString(errors, university.getUniversityName(), basePointer+".universityName", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfStringLengthBigger(errors, university.getUniversityName(), 255, basePointer+".universityName");
            rejectIfEmptyString(errors, university.getUniversitySettlement() != null? university.getUniversitySettlement().getId(): null, basePointer+".universitySettlement.id", ValidationMessageCodes.REQUIRED_CODE);
        }
    }
}
