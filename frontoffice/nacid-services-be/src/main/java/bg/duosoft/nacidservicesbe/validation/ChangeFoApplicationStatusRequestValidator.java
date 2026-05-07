package bg.duosoft.nacidservicesbe.validation;

import bg.duosoft.nacidfrontofficedto.services.common.application.ChangeFoApplicationStatusRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.08.2023
 * Time: 9:53
 */
@Component
@RequiredArgsConstructor
public class ChangeFoApplicationStatusRequestValidator implements Validator<ChangeFoApplicationStatusRequestDTO> {

    private final CommonApplicationService commonApplicationService;

    @Override
    public List<ValidationError> validate(ChangeFoApplicationStatusRequestDTO request, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, request.getStatusChangeType(), "statusChangeType", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmpty(errors, request.getApplicationId(), "applicationId", ValidationMessageCodes.REQUIRED_CODE);

        if(request.getStatusChangeType() != null){
            List<FoApplicationStatus> allowedStatuses =
                switch (request.getStatusChangeType()){
                    case ACCEPTANCE_DENIED -> Arrays.asList(FoApplicationStatus.SUBMITTED, FoApplicationStatus.SUBMITTED_WITH_SIGNATURE);
                    case REVERT_ACCEPTANCE_DENIED -> Arrays.asList(FoApplicationStatus.ACCEPTANCE_DENIED);
                };
            FoApplicationStatus currentStatus = commonApplicationService.getFoStatus(request.getApplicationId());
            if(!allowedStatuses.contains(currentStatus)){
                reject(errors, "applicationId", ValidationMessageCodes.INVALID_CODE);
            }
        }
        rejectIfEmptyString(errors, request.getInitiatingUser(), "initiatingUser", ValidationMessageCodes.REQUIRED_CODE);
        return errors;
    }
}
