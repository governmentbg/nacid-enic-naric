package bg.duosoft.nacidcoreapi.integration.captcha.validation;


import bg.duosoft.nacidcoreapi.integration.captcha.domain.GoogleCaptchaResponse;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.07.2022
 * Time: 13:47
 */
@Slf4j
@Component
public class GoogleCaptchaResponseValidator implements Validator<GoogleCaptchaResponse> {

    public static final float THRESHOLD = 0.5f;
    public static final String DEFAULT_POINTER = "reCaptcha";

    @Override
    public List<ValidationError> validate(GoogleCaptchaResponse response, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        String pointer = null;
        if(args != null && args.length>0){
            pointer = (String)args[0];
        }
        if(pointer == null){
            pointer = DEFAULT_POINTER;
        }
        if (Objects.isNull(response)) {
            reject(errors, pointer, "validation.recaptcha.validation.error");
        } else if (!response.isSuccess()) {
            if(response.getErrorCodes() != null && response.getErrorCodes().length  > 0){
                log.warn("Error in reCaptcha response: {}", Arrays.stream(response.getErrorCodes())
                        .filter(err -> err != null && err.name() != null)
                        .map(err -> err.name()).reduce((err1, err2) -> err1+", "+err2).get());
            }
            reject(errors, pointer, "validation.recaptcha.validation.error");
        } else if(response.getScore().compareTo(THRESHOLD) < 0){
            reject(errors, pointer, "validation.recaptcha.validation.error");
        }
        return errors;
    }
}
