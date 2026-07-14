package bg.duosoft.nacidcoreapi.integration.captcha.client;

import bg.duosoft.nacidcoreapi.integration.captcha.domain.GoogleCaptchaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleCaptchaClient", url = "${captcha.verify-endpoint}")
public interface GoogleCaptchaClient {
    @GetMapping
    GoogleCaptchaResponse validateCaptchaToken(@RequestParam("secret") String secret, @RequestParam("response") String captchaToken, @RequestParam("remoteip") String remoteIp);
}
