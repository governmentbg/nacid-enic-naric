package bg.duosoft.nacidcoreapi.integration.captcha.service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.07.2022
 * Time: 16:26
 */
public interface GoogleCaptchaService {

    void validateCaptcha(String captchaToken, String remoteIp, String pointer);
}
