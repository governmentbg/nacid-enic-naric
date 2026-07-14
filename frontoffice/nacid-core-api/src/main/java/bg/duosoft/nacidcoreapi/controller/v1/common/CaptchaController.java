package bg.duosoft.nacidcoreapi.controller.v1.common;

import bg.duosoft.nacidcoreapi.integration.captcha.service.GoogleCaptchaService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.07.2022
 * Time: 16:28
 */
@Slf4j
@RestController
@Api(tags = Tags.CAPTCHA)
@RequestMapping("/api/v1/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final GoogleCaptchaService googleCaptchaService;

    @GetMapping
    public void validateCaptcha(@RequestParam("captchaToken") String captchaToken, @RequestParam("remoteIp") String remoteIp, @RequestParam(value = "pointer", required = false) String pointer){
        googleCaptchaService.validateCaptcha(captchaToken, remoteIp, pointer);
    }
}
