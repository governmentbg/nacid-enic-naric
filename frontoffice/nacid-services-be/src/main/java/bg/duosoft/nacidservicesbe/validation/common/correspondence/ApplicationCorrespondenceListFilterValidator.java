package bg.duosoft.nacidservicesbe.validation.common.correspondence;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceListFilterDTO;
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
 * Time: 12:04
 */
@Component
public class ApplicationCorrespondenceListFilterValidator implements Validator<ApplicationCorrespondenceListFilterDTO> {

    @Override
    public List<ValidationError> validate(ApplicationCorrespondenceListFilterDTO correspondenceFilter, Object... args) {
        List<ValidationError> validationErrors = new ArrayList<>();
        rejectIfEmpty(validationErrors, correspondenceFilter.getPage(), "page", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(validationErrors, correspondenceFilter.getPageSize(), "pageSize", ValidationMessageCodes.REQUIRED_CODE);
        return validationErrors;
    }
}
