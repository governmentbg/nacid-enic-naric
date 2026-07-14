package bg.duosoft.nacid.backoffice.core.data.validation.address;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressValidator implements Validator<AddressDTO> {

    public List<ValidationError> validateRequiredFields(AddressDTO obj, List<AddressFields> requiredFields) {
        return validate(obj, requiredFields);
    }

    @Override
    public List<ValidationError> validate(AddressDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        List<AddressFields> requiredFields = (List<AddressFields>) args[0];

        rejectIfTrue(errors, Objects.isNull(obj), "address", "validation.field.required");

        if (Objects.nonNull(obj)) {

            if (requiredFields.contains(AddressFields.COUNTRY)) {
                if (Objects.nonNull(obj.getCountry())) {
                    rejectIfTrue(errors, !StringUtils.hasText(obj.getCountry().getId()), "address.country.id", "validation.field.required");
                } else {
                    reject(errors, "address.country.id", "validation.field.required");
                }

                if (Objects.nonNull(obj.getCountry()) && StringUtils.hasText(obj.getCountry().getId())) {
                    if (obj.getCountry().getId().equals("BG")) {
                        if (requiredFields.contains(AddressFields.SETTLEMENT)) {
                            if (Objects.isNull(obj.getSettlement())) {
                                reject(errors, "address.settlement.id", "validation.field.required");
                            } else {
                                rejectIfTrue(errors, !StringUtils.hasText(obj.getSettlement().getId()), "address.settlement.id", "validation.field.required");
                            }
                        }
                    } else {
                        if (requiredFields.contains(AddressFields.CITY)) {
                            rejectIfEmptyString(errors, obj.getCity(), "address.city", "validation.field.required");
                        }
                        if (StringUtils.hasText(obj.getCity())) {
                            rejectIfTrue(errors, obj.getCity().length() > 50, "address.city", "validation.charCount.invalid.50");
                        }
                    }
                }
            }

            if (requiredFields.contains(AddressFields.PHONE)) {
                rejectIfEmptyString(errors, obj.getPhone(), "address.phone", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getPhone())) {
                rejectIfTrue(errors, obj.getPhone().length() > 70, "address.phone", "validation.charCount.invalid.70");
            }

            if (requiredFields.contains(AddressFields.ADDRESS)) {
                rejectIfEmptyString(errors, obj.getAddress(), "address.address", "validation.field.required");
            }

            if (requiredFields.contains(AddressFields.POST_CODE)) {
                rejectIfEmptyString(errors, obj.getPostCode(), "address.postCode", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getPostCode())) {
                rejectIfTrue(errors, obj.getPostCode().length() > 12, "address.postCode", "validation.charCount.invalid.12");
            }

            if (requiredFields.contains(AddressFields.EMAIL)) {
                rejectIfEmptyString(errors, obj.getEmail(), "address.email", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getEmail())) {
                rejectIfTrue(errors, obj.getEmail().length() > 80, "address.email", "validation.charCount.invalid.80");
            }

            if (requiredFields.contains(AddressFields.FAX)) {
                rejectIfEmptyString(errors, obj.getFax(), "address.fax", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getFax())) {
                rejectIfTrue(errors, obj.getFax().length() > 70, "address.fax", "validation.charCount.invalid.70");
            }

            if (requiredFields.contains(AddressFields.POST_BOX)) {
                rejectIfEmptyString(errors, obj.getPostBox(), "address.postBox", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getPostBox())) {
                rejectIfTrue(errors, obj.getPostBox().length() > 100, "address.postBox", "validation.charCount.invalid.100");
            }

            if (requiredFields.contains(AddressFields.CONTACT_PERSON)) {
                rejectIfEmptyString(errors, obj.getContactPerson(), "address.contactPerson", "validation.field.required");
            }
            if (StringUtils.hasText(obj.getContactPerson())) {
                rejectIfTrue(errors, obj.getContactPerson().length() > 255, "address.contactPerson", "validation.charCount.invalid.255");
            }
        }

        return errors;
    }
}
