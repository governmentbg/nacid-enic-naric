package bg.duosoft.nacidservicesbe.validation.documentdelivery;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.applicantdetails.CommonApplicantDetailsValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 12:18
 */
@Component
@RequiredArgsConstructor
public class DocDeliveryApplicantDetailsValidator implements Validator<CommonApplicantDetailsDTO> {

    private final CommonApplicantDetailsValidator commonApplicantDetailsValidator;

    @Override
    public List<ValidationError> validate(CommonApplicantDetailsDTO applicantDetailsDTO, Object... objects) {
        List<ValidationError> errors = commonApplicantDetailsValidator.validate(applicantDetailsDTO, false, false);
        commonApplicantDetailsValidator.validateContactAddressWithFlag(errors, applicantDetailsDTO.getContactAddress(), applicantDetailsDTO.getHasContactAddress(), true);
        return errors;
    }
}
