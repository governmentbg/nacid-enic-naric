package bg.duosoft.nacidcoreclient.client.captcha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.07.2022
 * Time: 16:56
 */
public interface BaseCaptchaClient {

    @GetMapping
    void validateCaptcha(@RequestParam("captchaToken") String captchaToken, @RequestParam("remoteIp") String remoteIp, @RequestParam(value = "pointer", required = false) String pointer);
}
