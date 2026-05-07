package bg.duosoft.nacidcoreapi.integration.captcha.service;

import bg.duosoft.nacidcoreapi.integration.captcha.client.GoogleCaptchaClient;
import bg.duosoft.nacidcoreapi.integration.captcha.domain.GoogleCaptchaResponse;
import bg.duosoft.nacidcoreapi.integration.captcha.validation.GoogleCaptchaResponseValidator;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.07.2022
 * Time: 16:26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleCaptchaServiceImpl implements GoogleCaptchaService {

    private final GoogleCaptchaClient googleCaptchaClient;
    private final GoogleCaptchaResponseValidator googleCaptchaResponseValidator;

    @Value("${captcha.secret-key}")
    private String secretKey;

    @Value("${captcha.enabled}")
    private boolean enabled;

    @Override
    public void validateCaptcha(String captchaToken, String remoteIp, String pointer) {
        if(enabled) {
            GoogleCaptchaResponse response;
            try {
                response = googleCaptchaClient.validateCaptchaToken(secretKey, captchaToken, remoteIp);
            } catch (Exception e) {
                log.warn("Problem calling googleCaptchaClient.validateCaptchaToken", e);
                return;
            }
            BadRequestValidator.validateRequest(googleCaptchaResponseValidator, response, pointer);
        }
    }
}
