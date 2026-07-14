package bg.duosoft.nacidservicesbe.validation.publicaccess;

import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessDetailsDTO;
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
public class PublicAccessDetailsValidator implements Validator<PublicAccessDetailsDTO> {

    @Override
    public List<ValidationError> validate(PublicAccessDetailsDTO form, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmptyString(errors, form.getAbout(), ValidationMessageCodes.REQUIRED_CODE);

        return errors;
    }
}
