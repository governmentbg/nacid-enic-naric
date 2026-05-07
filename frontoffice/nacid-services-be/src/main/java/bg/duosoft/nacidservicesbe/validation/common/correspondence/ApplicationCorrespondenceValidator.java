package bg.duosoft.nacidservicesbe.validation.common.correspondence;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2023
 * Time: 11:51
 */
@Component
public class ApplicationCorrespondenceValidator implements Validator<ApplicationCorrespondenceDTO> {

    @Override
    public List<ValidationError> validate(ApplicationCorrespondenceDTO correspondence, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, correspondence.getApplicationId(), "applicationId", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(errors, correspondence.getRefId(), "refId", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, correspondence.getAbout(), "about", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, correspondence.getRegistrationNumber(), "registrationNumber", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(errors, correspondence.getRegistrationDate(), "registrationDate", ValidationMessageCodes.REQUIRED_CODE);
        return errors;
    }
}
