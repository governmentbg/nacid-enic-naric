package bg.duosoft.nacid.backoffice.core.be.validation.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonalNacidIdService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CivilIdTypeService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonUtils;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.base.EikValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator<PersonDTO>, EikValidator<PersonDTO> {

    private final CivilIdTypeService civilIdTypeService;
    private final PersonService personService;
    private final PersonalNacidIdService personalNacidIdService;

    @Override
    public List<ValidationError> validate(PersonDTO person, Object... additionalArgs) {
        List<ValidationError> errors = new ArrayList<>();

        rejectIfTrue(errors, CommonUtils.isEmpty(person.getLegalType()), "legalType", "validation.field.required");
        if (CollectionUtils.isEmpty(errors)) {
            if (PersonUtils.isNaturalPerson(person)) {
                validateNaturalPerson(errors, person);
            } else if (PersonUtils.isLegalEntity(person)) {
                validateLegalEntity(errors, person);
            }
            validateExistingCivilId(errors, person);

            Integer id = person.getId();
            if (Objects.nonNull(id)) {
                PersonDTO existingPerson = personService.selectById(id);
                rejectIfEmpty(errors, existingPerson, "personObject");
                personTypeChangeValidation(person, errors, existingPerson);
            }

        }
        return errors;
    }

    private void personTypeChangeValidation(PersonDTO person, List<ValidationError> errors, PersonDTO existingPerson) {
        if (Objects.nonNull(existingPerson) && CollectionUtils.isEmpty(errors)) {
            LegalType existingLegalType = LegalType.selectByCode(existingPerson.getLegalType().getId());
            LegalType newLegalType = LegalType.selectByCode(person.getLegalType().getId());
            rejectIfTrue(errors, existingLegalType != newLegalType, "personLegalType", "m.person.legalTypeCannotBeChanged");

            if (CollectionUtils.isEmpty(errors) && newLegalType == LegalType.LEGAL_ENTITY) {
                LegalNatureType existingLegalNatureType = LegalNatureType.selectByCode(existingPerson.getLegalNatureType().getId());
                LegalNatureType newLegalNatureType = LegalNatureType.selectByCode(person.getLegalNatureType().getId());
                rejectIfTrue(errors, existingLegalNatureType != newLegalNatureType, "personLegalNatureType", "m.person.legalNatureTypeCannotBeChanged");
            }
        }
    }

    private void validateExistingCivilId(List<ValidationError> errors, PersonDTO person) {
        if (Objects.equals(true, person.getIsActive()) && CollectionUtils.isEmpty(errors)) {

            List<PersonDTO> existingPersons = personService.selectByCivilId(
                    CommonUtils.selectId(person.getCivilIdType()),
                    person.getCivilId(),
                    CommonUtils.selectId(person.getForeignIdentifierType()),
                    CommonUtils.selectId(person.getForeignIdentifierCountry()),
                    true);

            if (!CollectionUtils.isEmpty(existingPersons) && !isTheSamePerson(person, existingPersons)) {
                reject(errors, "civilId", "validation.person.existingCivilId");
            }
        }
    }

    private static boolean isTheSamePerson(PersonDTO person, List<PersonDTO> existingPersons) {
        return existingPersons.size() == 1 && existingPersons.get(0).getId().equals(person.getId());
    }

    private void validateNaturalPerson(List<ValidationError> errors, PersonDTO person) {
//        rejectIfTrue(errors, CommonUtils.isEmpty(person.getCitizenship()), "citizenship.id", "validation.field.required");
//        rejectIfEmpty(errors, person.getBirthDate(), "birthDate", "validation.field.required");

        validateEmail(errors, person.getEmail());
        validateNaturalPersonNames(errors, person);
        validateOriginData(errors, person);
        validateNaturalPersonCivilId(errors, person);
    }

    private void validateOriginData(List<ValidationError> errors, PersonDTO person) {

        boolean hasOriginCountry = !CommonUtils.isEmpty(person.getOriginCountry());
        rejectIfFalse(errors, hasOriginCountry, "originCountry.id", "validation.field.required");
        /*if (hasOriginCountry) {
            String originCountryId = person.getOriginCountry().getId();
            if (DefaultValue.BG_COUNTRY_CODE.equalsIgnoreCase(originCountryId)) {
                boolean isEmptyOriginSettlement = Objects.isNull(person.getOriginSettlement()) || !StringUtils.hasText(person.getOriginSettlement().getId());
                rejectIfTrue(errors, isEmptyOriginSettlement, "originSettlement.id", "validation.field.required");
            } else {
                rejectIfEmptyString(errors, person.getOriginCity(), "originCity", "validation.field.required");
            }
        }*/
    }

    private void validateEmail(List<ValidationError> errors, String email) {
        if (StringUtils.hasText(email)) {
            rejectIfNotMatchRegex(errors, email, RegexUtils.EMAIL_REGEX, "email", "validation.field.email.bad.format");
        }
    }

    private void validateNaturalPersonNames(List<ValidationError> errors, PersonDTO person) {
        rejectIfEmptyString(errors, person.getFirstName(), "firstName", "validation.field.required");

        boolean hasOriginCountry = !CommonUtils.isEmpty(person.getOriginCountry());
        if (hasOriginCountry) {
            String originCountryId = person.getOriginCountry().getId();
            if (DefaultValue.BG_COUNTRY_CODE.equalsIgnoreCase(originCountryId)) {
                rejectIfEmptyString(errors, person.getLastName(), "lastName", "validation.field.required");
            }
        }
    }

    private void validateNaturalPersonCivilId(List<ValidationError> errors, PersonDTO person) {
        String civilId = person.getCivilId();
        rejectIfEmptyString(errors, civilId, "civilId", "validation.field.required");

        boolean hasCivilIdType = !CommonUtils.isEmpty(person.getCivilIdType());
        rejectIfTrue(errors, !hasCivilIdType, "civilIdType.id", "validation.field.required");
        if (hasCivilIdType) {
            String civilIdTypeId = person.getCivilIdType().getId();
            CivilIdTypeDTO civilIdTypeDTO = civilIdTypeService.selectById(civilIdTypeId);
            rejectIfEmpty(errors, civilIdTypeDTO, "civilIdType.id", "validation.field.invalid");
            if (Objects.nonNull(civilIdTypeDTO)) {
                CivilIdType civilIdTypeEnum = CivilIdType.selectByCode(civilIdTypeDTO.getId());
                switch (civilIdTypeEnum) {
                    case EGN -> {
                        if (StringUtils.hasText(civilId)) {
                            boolean isValidEGN = CivilIdUtils.validateEGN(civilId);
                            rejectIfFalse(errors, isValidEGN, "civilId", "validation.field.invalid");
                            if (isValidEGN) {
                                LocalDate birthDate = person.getBirthDate();
                                if (Objects.nonNull(birthDate)) {
                                    LocalDate extractedBirthDate = CivilIdUtils.getBirthDate(civilId);
                                    rejectIfFalse(errors, birthDate.isEqual(extractedBirthDate), "birthDate", "validation.field.invalid");
                                }
                            }
                        }
                    }
                    case LNCH -> {
                        if (StringUtils.hasText(civilId)) {
                            rejectIfFalse(errors, CivilIdUtils.validateLNCH(civilId), "civilId", "validation.field.invalid");
                        }
                    }
                    case FOREIGN_COUNTRY_ID -> {
                        rejectIfTrue(errors, CommonUtils.isEmpty(person.getForeignIdentifierCountry()), "foreignIdentifierCountry.id", "validation.field.required");
                        if (StringUtils.hasText(civilId)) {
                            rejectIfTrue(errors, civilId.length() > MAX_INPUT_LENGTH_XXS, "civilId", "validation.charCount.invalid.50");
                        }

                        String foreignIdentifierType = CommonUtils.selectId(person.getForeignIdentifierType());
                        if (!StringUtils.hasText(foreignIdentifierType)) {
                            reject(errors, "foreignIdentifierType.id", "validation.field.required");
                        } else {
                            ForeignIdType foreignIdTypeEnum = ForeignIdType.selectByCode(foreignIdentifierType);
                            switch (foreignIdTypeEnum) {
                                case NACID_GENERATED_NUMBER -> {
                                    if (StringUtils.hasText(civilId)) {
                                        PersonalNacidIdDTO personalNacidIdDTO = personalNacidIdService.selectByValue(civilId);
                                        rejectIfEmpty(errors, personalNacidIdDTO, "civilId", "validation.field.invalid");
                                    }
                                }
                            }
                        }
                    }
                    default -> reject(errors, "civilIdType.id", "validation.field.invalid");
                }
            }
        }

    }

    private void validateLegalEntityCivilId(List<ValidationError> errors, PersonDTO person) {
        String civilId = person.getCivilId();
        rejectIfEmptyString(errors, civilId, "civilId", "validation.field.required");

        boolean hasCivilIdType = !CommonUtils.isEmpty(person.getCivilIdType());
        rejectIfTrue(errors, !hasCivilIdType, "civilIdType.id", "validation.field.required");
        if (hasCivilIdType) {
            String civilIdTypeId = person.getCivilIdType().getId();
            CivilIdTypeDTO civilIdTypeDTO = civilIdTypeService.selectById(civilIdTypeId);
            rejectIfEmpty(errors, civilIdTypeDTO, "civilIdType.id", "validation.field.invalid");
            if (Objects.nonNull(civilIdTypeDTO)) {
                CivilIdType civilIdTypeEnum = CivilIdType.selectByCode(civilIdTypeDTO.getId());
                switch (civilIdTypeEnum) {
                    case EIK -> {
                        if (StringUtils.hasText(civilId)) {
                            validateEIK(errors, civilId, "civilId");
                        }
                    }
                    default -> reject(errors, "civilIdType.id", "validation.field.invalid");
                }
            }
        }

    }

    private void validateLegalEntity(List<ValidationError> errors, PersonDTO person) {
        rejectIfEmptyString(errors, person.getLegalName(), "legalName", "validation.field.required");

        validateEmail(errors, person.getEmail());
        validateOriginData(errors, person);
        validateLegalEntityCivilId(errors, person);
    }

}
