package bg.duosoft.nacidservicesbe.validation.unichecks;

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
 * Date: 24.01.2023
 * Time: 16:22
 */
@Component
@RequiredArgsConstructor
public class UniChecksApplicantDetailsValidator implements Validator<CommonApplicantDetailsDTO> {

    private final CommonApplicantDetailsValidator commonApplicantDetailsValidator;

    @Override
    public List<ValidationError> validate(CommonApplicantDetailsDTO applicantDetailsDTO, Object... objects) {
        List<ValidationError> errors = commonApplicantDetailsValidator.validate(applicantDetailsDTO, true);
        commonApplicantDetailsValidator.validateContactAddress(errors, applicantDetailsDTO.getContactAddress(), true);
        return errors;
    }
}
