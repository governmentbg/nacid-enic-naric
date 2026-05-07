package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCheckupRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.ApplicationCheckupRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.01.2023
 * Time: 18:29
 */
@Api(tags = Tags.APP_CHECKUP)
@RestController
@RequestMapping("/api/v1/app-checkup")
@RequiredArgsConstructor
public class ApplicationCheckupController {

    private final CommonApplicationService commonApplicationService;
    private final ApplicationCheckupRequestValidator applicationCheckupRequestValidator;

    @GetMapping("/subtype")
    public ApplicationSubtype findAppSubtype(HttpServletRequest request, ApplicationCheckupRequestDTO checkupRequest){
        checkupRequest.setRemoteIp(request.getRemoteAddr());
        BadRequestValidator.validateRequest(applicationCheckupRequestValidator, checkupRequest);
        return commonApplicationService.getApplicationSubtype(checkupRequest.getDossierNumber(), checkupRequest.getAccessCode());
    }
}
