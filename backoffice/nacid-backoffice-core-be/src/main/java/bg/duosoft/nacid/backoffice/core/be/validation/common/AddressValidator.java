package bg.duosoft.nacid.backoffice.core.be.validation.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.AddressService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressValidator implements Validator<AddressDTO> {

    private final AddressService addressService;

    @Override
    public List<ValidationError> validate(AddressDTO address, Object... additionalArgs) {
        List<ValidationError> errors = new ArrayList<>();
        baseValidation(errors, address);

        boolean existAddressType = !CommonUtils.isEmpty(address.getAddressType());
        if (existAddressType) {
            AddressType addressType = AddressType.selectByCode(address.getAddressType().getId());
            switch (addressType) {
                case CONTACT -> contactAddressValidation(errors, address);
                case DOCUMENT -> documentRecipientAddressValidation(errors, address);
            }
        }

        Integer id = address.getId();
        if (Objects.nonNull(id)) {
            AddressDTO existingAddress = addressService.selectById(id);
            rejectIfEmpty(errors, existingAddress, "addressObject");
            addressTypeChangeValidation(address, errors, existingAddress);
        }

        return errors;
    }

    private void addressTypeChangeValidation(AddressDTO address, List<ValidationError> errors, AddressDTO existingAddress) {
        if (Objects.nonNull(existingAddress) && CollectionUtils.isEmpty(errors)) {
            AddressType existingAddressType = AddressType.selectByCode(existingAddress.getAddressType().getId());
            AddressType newAddressType = AddressType.selectByCode(address.getAddressType().getId());
            rejectIfTrue(errors, existingAddressType != newAddressType, "addressType.id", "m.address.typeCannotBeChanged");
        }
    }

    private void baseValidation(List<ValidationError> errors, AddressDTO address) {
        rejectIfTrue(errors, CommonUtils.isEmpty(address.getAddressType()), "addressType.id", "validation.field.required");
        rejectIfEmptyString(errors, address.getAddress(), "address", "validation.field.required");
        requiredCountryAndCityValidation(errors, address);

        String email = address.getEmail();
        if (StringUtils.hasText(email)) {
            rejectIfNotMatchRegex(errors, email, RegexUtils.EMAIL_REGEX, "email", "validation.field.email.bad.format");
        }
    }

    private void contactAddressValidation(List<ValidationError> errors, AddressDTO address) {
        rejectIfEmptyString(errors, address.getEmail(),"email","validation.field.required");
        rejectIfEmptyString(errors, address.getPhone(),"phone","validation.field.required");
    }

    private void documentRecipientAddressValidation(List<ValidationError> errors, AddressDTO address) {
        rejectIfEmptyString(errors,address.getContactPerson(),"contactPerson","validation.field.required");
    }

    private void requiredCountryAndCityValidation(List<ValidationError> errors, AddressDTO address) {
        boolean hasCountry = !CommonUtils.isEmpty(address.getCountry());
        rejectIfFalse(errors, hasCountry, "country.id", "validation.field.required");

        if (hasCountry) {
            String countryId = address.getCountry().getId();
            if (DefaultValue.BG_COUNTRY_CODE.equalsIgnoreCase(countryId)) {
                boolean isEmptySettlement = Objects.isNull(address.getSettlement()) || !StringUtils.hasText(address.getSettlement().getId());
                rejectIfTrue(errors, isEmptySettlement, "settlement.id", "validation.field.required");
            } else {
                rejectIfEmptyString(errors, address.getCity(), "city", "validation.field.required");
            }
        } else {
            rejectIfEmptyString(errors, address.getCity(), "city", "validation.field.required");
        }
    }

}
