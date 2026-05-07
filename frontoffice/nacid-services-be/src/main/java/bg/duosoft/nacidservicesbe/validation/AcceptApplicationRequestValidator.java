package bg.duosoft.nacidservicesbe.validation;

import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 31.01.2023
 * Time: 15:54
 */
@Component
@RequiredArgsConstructor
public class AcceptApplicationRequestValidator implements Validator<AcceptApplicationRequestDTO> {

    private final CommonApplicationService commonApplicationService;

    @Override
    public List<ValidationError> validate(AcceptApplicationRequestDTO acceptApplicationRequest, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, acceptApplicationRequest.getApplicationId(), "applicationId", ValidationMessageCodes.REQUIRED_CODE);
        if(acceptApplicationRequest.getApplicationId() != null){
            FoApplicationStatus currentStatus = commonApplicationService.getFoStatus(acceptApplicationRequest.getApplicationId());
            if(!currentStatus.equals(FoApplicationStatus.SUBMITTED) && !currentStatus.equals(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE)){
                reject(errors, "applicationId", ValidationMessageCodes.INVALID_CODE);
            }
        }
        rejectIfEmptyString(errors, acceptApplicationRequest.getEntryNumber(), "entryNumber", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(errors, acceptApplicationRequest.getEntryDate(), "entryDate", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, acceptApplicationRequest.getInitiatingUser(), "initiatingUser", ValidationMessageCodes.REQUIRED_CODE);
        return errors;
    }
}
