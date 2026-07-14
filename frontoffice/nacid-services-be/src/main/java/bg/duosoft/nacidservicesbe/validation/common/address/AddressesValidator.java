package bg.duosoft.nacidservicesbe.validation.common.address;

import bg.duosoft.nacidfrontofficedto.address.BaseAddress;
import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.utils.NomenclatureConstants;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.09.2022
 * Time: 11:05
 */
public interface AddressesValidator<T> extends Validator<T> {


    default void validateBaseAddress(List<ValidationError> errors, BaseAddress baseAddress, String basePointer, boolean postCodeRequired) {
        if(baseAddress.getCountry() == null){
            rejectIfEmpty(errors, baseAddress.getCountry().getId(), basePointer + ".country.id", ValidationMessageCodes.REQUIRED_CODE);
        } else {
            rejectIfEmptyString(errors, baseAddress.getCountry().getId(), basePointer + ".country.id", ValidationMessageCodes.REQUIRED_CODE);
            if(baseAddress.getCountry().getId() != null && baseAddress.getCountry().getId().equals(DefaultValue.BG_COUNTRY_CODE)){
                rejectIfEmptyString(errors, baseAddress.getSettlement() != null? baseAddress.getSettlement().getId(): null, basePointer+".settlement.id", ValidationMessageCodes.REQUIRED_CODE);
            } else {
                rejectIfEmptyString(errors, baseAddress.getCity(), basePointer+".city", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfStringLengthBigger(errors, baseAddress.getCity(), 50, basePointer+".city", ValidationMessageCodes.INVALID_LENGTH_MAX_50);
            }
        }

        rejectIfEmptyString(errors, baseAddress.getAddress(), basePointer+".address", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, baseAddress.getPhone(), basePointer+".phone", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfStringLengthBigger(errors, baseAddress.getPhone(), 70, basePointer+".phone");
        if(postCodeRequired) {
            rejectIfEmptyString(errors, baseAddress.getPostCode(), basePointer + ".postCode", ValidationMessageCodes.REQUIRED_CODE);
        }
        rejectIfStringLengthBigger(errors, baseAddress.getPostCode(), 12, basePointer+".postCode");

        rejectIfNotMatchRegex(errors, baseAddress.getPostCode(), RegexUtils.POST_CODE_VALIDATION_REGEX, basePointer+".postCode", ValidationMessageCodes.INVALID_CODE);
    }

    default void validateContactAddress(List<ValidationError> errors, ContactAddressDTO contactAddress, boolean postCodeRequired){
        if(contactAddress == null){
            errors.add(ValidationError.builder().pointer("contactAddress").message("validation.field.required").build());
        } else {
            validateBaseAddress(errors, contactAddress, "contactAddress", postCodeRequired);
            rejectIfEmpty(errors, contactAddress.getEmail(), "contactAddress.email", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfStringLengthBigger(errors, contactAddress.getEmail(), 80, "contactAddress.email");
            rejectIfNotMatchRegex(errors, contactAddress.getEmail(), RegexUtils.EMAIL_REGEX, "contactAddress.email", ValidationMessageCodes.INVALID_CODE);
            rejectIfStringLengthBigger(errors, contactAddress.getPostBox(), 100, "contactAddress.postBox");
            rejectIfStringLengthBigger(errors, contactAddress.getFax(), 70, "contactAddress.fax");
        }
    }

    default void validateContactAddressWithFlag(List<ValidationError> errors, ContactAddressDTO contactAddress, Boolean hasContactAddress, boolean postCodeRequired){
        if(Boolean.TRUE.equals(hasContactAddress)) {
            validateContactAddress(errors, contactAddress, postCodeRequired);
        } else if(contactAddress != null){
            errors.add(ValidationError.builder().pointer("contactAddress").message(ValidationMessageCodes.INVALID_CODE).build());
        }
    }

    default void validateReceiverAddress(List<ValidationError> errors, ReceiverAddressDTO receiverAddress, DocumentReceiveMethodDTO resultReceive, String basePointer){
        if(resultReceive != null && Boolean.TRUE.equals(resultReceive.getDocumentRecipient())){
            if(receiverAddress == null){
                errors.add(ValidationError.builder().pointer(basePointer+".receiverAddress").message(ValidationMessageCodes.REQUIRED_CODE).build());
            } else {
                validateBaseAddress(errors, receiverAddress, basePointer+".receiverAddress", true);
                rejectIfEmptyString(errors, receiverAddress.getName(), basePointer+".receiverAddress.name", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfStringLengthBigger(errors, receiverAddress.getName(), 255, basePointer+".receiverAddress.name");
                if(NomenclatureConstants.RESULT_RECEIVE_INTERNATIONAL_DELIVERY.equals(resultReceive.getId())) {
                    rejectIfNotMatchRegex(errors, receiverAddress.getName(), RegexUtils.NAME_CYR_OR_LAT_VALIDATION_REGEX, basePointer+".receiverAddress.name", ValidationMessageCodes.INVALID_CODE);
                } else {
                    rejectIfNotMatchRegex(errors, receiverAddress.getName(), RegexUtils.NAME_VALIDATION_REGEX, basePointer+".receiverAddress.name", ValidationMessageCodes.INVALID_CODE);
                }
            }
        }
    }
}
