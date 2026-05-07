package bg.duosoft.nacidservicesbe.validation;

import bg.duosoft.nacidcoreclient.client.captcha.CaptchaClient;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCheckupRequestDTO;
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
 * Date: 11.01.2023
 * Time: 11:38
 */
@Component
@RequiredArgsConstructor
public class ApplicationCheckupRequestValidator implements Validator<ApplicationCheckupRequestDTO> {

    private final CaptchaClient captchaClient;

    @Override
    public List<ValidationError> validate(ApplicationCheckupRequestDTO appCheckup, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, appCheckup, "dossierNumber", ValidationMessageCodes.REQUIRED_CODE);
        if(appCheckup != null) {
            rejectIfEmptyString(errors, appCheckup.getDossierNumber(), "dossierNumber", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfNotMatchRegex(errors, appCheckup.getDossierNumber(), "^[\\w\\-]+/\\d{2}\\.\\d{2}\\.\\d{4}$", "dossierNumber", ValidationMessageCodes.INVALID_CODE);
            rejectIfEmptyString(errors, appCheckup.getAccessCode(), "accessCode", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfEmptyString(errors, appCheckup.getCaptchaToken(), "captchaToken", ValidationMessageCodes.REQUIRED_CODE);

        }
        if(errors.size() == 0){
            captchaClient.validateCaptcha(appCheckup.getCaptchaToken(), appCheckup.getRemoteIp(), "captchaToken");
        }
        return errors;
    }
}
