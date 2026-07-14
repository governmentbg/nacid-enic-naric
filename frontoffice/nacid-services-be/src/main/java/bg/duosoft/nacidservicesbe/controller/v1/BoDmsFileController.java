package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidcoreclient.client.captcha.CaptchaClient;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesAdminService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 16:42
 */
@Api(tags = Tags.BO_DMS_FILE)
@RestController
@RequestMapping("/api/v1/bo-dms-file")
@RequiredArgsConstructor
public class BoDmsFileController {

    private final BoPublicServicesAdminService boPublicServicesAdminService;
    private final CaptchaClient captchaClient;

    @GetMapping
    public ResponseEntity<byte[]> getFileContentFromBoDms(HttpServletRequest request, @RequestParam Integer docId, @RequestParam Integer fileId, @RequestParam String captchaToken){
        captchaClient.validateCaptcha(captchaToken, request.getRemoteHost(), "captchaToken");
        return boPublicServicesAdminService.downloadDmsFile(docId, fileId);
    }
}
