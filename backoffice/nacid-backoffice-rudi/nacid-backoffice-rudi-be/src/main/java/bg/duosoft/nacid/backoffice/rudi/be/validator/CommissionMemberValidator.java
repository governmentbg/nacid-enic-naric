package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressFields;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.PersonIdentifierValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommissionMemberValidator implements PersonIdentifierValidator<CommissionMemberDTO> {

    private final AddressValidator addressValidator;

    @Override
    public List<ValidationError> validate(CommissionMemberDTO obj, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        CivilIdType civilIdType = Objects.nonNull(obj.getCivilIdType()) && Objects.nonNull(obj.getCivilIdType().getId()) ? CivilIdType.selectByCode(obj.getCivilIdType().getId()) : null;
        validatePersonalId(errors, Objects.nonNull(civilIdType), obj.getCivilId(), civilIdType, "civilId");

        List<AddressFields> requiredAddressFields = new ArrayList<>();
        addressValidator.validate(errors, obj.getAddress(), requiredAddressFields);

        rejectIfEmptyString(errors, obj.getFirstName(), "firstName", "validation.field.required");
        if (StringUtils.hasText(obj.getFirstName())) {
            rejectIfTrue(errors, obj.getFirstName().length() > 100, "firstName", "validation.charCount.invalid.100");
        }
        if (StringUtils.hasText(obj.getMiddleName())) {
            rejectIfTrue(errors, obj.getMiddleName().length() > 100, "middleName", "validation.charCount.invalid.100");
        }
        rejectIfEmptyString(errors, obj.getLastName(), "lastName", "validation.field.required");
        if (StringUtils.hasText(obj.getLastName())) {
            rejectIfTrue(errors, obj.getLastName().length() > 100, "lastName", "validation.charCount.invalid.100");
        }
        rejectIfEmptyString(errors, obj.getCommissionPosition().getId(), "commissionPosition.id", "validation.field.required");

        if (StringUtils.hasText(obj.getDegree())) {
            rejectIfTrue(errors, obj.getDegree().length() > 30, "degree", "validation.charCount.invalid.30");
        }
        if (StringUtils.hasText(obj.getInstitution())) {
            rejectIfTrue(errors, obj.getInstitution().length() > 255, "institution", "validation.charCount.invalid.255");
        }
        if (StringUtils.hasText(obj.getDivision())) {
            rejectIfTrue(errors, obj.getDivision().length() > 255, "division", "validation.charCount.invalid.255");
        }
        if (StringUtils.hasText(obj.getTitle())) {
            rejectIfTrue(errors, obj.getTitle().length() > 255, "title", "validation.charCount.invalid.255");
        }
        if (StringUtils.hasText(obj.getIban())) {
            rejectIfTrue(errors, obj.getIban().length() > 30, "iban", "validation.charCount.invalid.30");
        }
        if (StringUtils.hasText(obj.getBic())) {
            rejectIfTrue(errors, obj.getBic().length() > 10, "bic", "validation.charCount.invalid.10");
        }

        return errors;
    }
}
