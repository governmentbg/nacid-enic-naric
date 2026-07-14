package bg.duosoft.nacidservicesbe.validation.signal;

import bg.duosoft.nacidfrontofficedto.services.signal.SignalDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 16:48
 */
@Component
public class SignalDetailsValidator implements Validator<SignalDetailsDTO> {

    @Override
    public List<ValidationError> validate(SignalDetailsDTO form, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmptyString(errors, form.getViolationDescription(), ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, form.getViolationPlace(), ValidationMessageCodes.REQUIRED_CODE);

        return errors;
    }
}
