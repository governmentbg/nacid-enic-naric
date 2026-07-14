package bg.duosoft.nacidservicesbe.validation;

import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
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
 * Time: 15:57
 */
@Component
@RequiredArgsConstructor
public class RevertApplicationStatusToDraftRequestValidator implements Validator<RevertApplicationStatusToDraftRequestDTO> {

    private final CommonApplicationService commonApplicationService;

    @Override
    public List<ValidationError> validate(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();

        rejectIfEmpty(errors, revertApplicationStatusToDraftRequest.getApplicationId(), "applicationId", ValidationMessageCodes.REQUIRED_CODE);
        if(revertApplicationStatusToDraftRequest.getApplicationId() != null){
            boolean hasAcceptedStatus = commonApplicationService.applicationHasFoStatus(revertApplicationStatusToDraftRequest.getApplicationId(), FoApplicationStatus.ACCEPTED);
            if(hasAcceptedStatus){
                reject(errors, "applicationId", ValidationMessageCodes.INVALID_CODE);
            }
        }
        rejectIfEmptyString(errors, revertApplicationStatusToDraftRequest.getRevertMessage(), "revertMessage", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, revertApplicationStatusToDraftRequest.getInitiatingUser(), "initiatingUser", ValidationMessageCodes.REQUIRED_CODE);
        return errors;
    }
}
