package bg.duosoft.nacidservicesbe.validation;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListFilterDTO;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 14:02
 */
@Component
public class ApplicationListFilterValidator implements Validator<ApplicationListFilterDTO> {

    @Override
    public List<ValidationError> validate(ApplicationListFilterDTO applicationListFilterDTO, Object... objects) {
        List<ValidationError> validationErrors = new ArrayList<>();
        rejectIfEmpty(validationErrors, applicationListFilterDTO.getPage(), "page", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(validationErrors, applicationListFilterDTO.getPageSize(), "pageSize", ValidationMessageCodes.REQUIRED_CODE);
        return validationErrors;
    }
}
