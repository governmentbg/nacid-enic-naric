package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidcoreclient.client.captcha.CaptchaClient;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCertificateDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.controller.utils.AccessUtils;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesAdminService;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 16:38
 */
@Api(tags = Tags.APP_CHECKUP)
@RestController
@RequestMapping("/api/v1/app-certificate")
@RequiredArgsConstructor
public class ApplicationCertificateController {

    private final CommonApplicationService commonApplicationService;
    private final BoPublicServicesAdminService boPublicServicesAdminService;
    private final CaptchaClient captchaClient;

    @GetMapping
    public ApplicationCertificateDTO getApplicationCertificate(HttpServletRequest request, @RequestParam(required = false) Integer applicationId,
                                                                    @RequestParam(required = false) String dossierNumber,
                                                                    @RequestParam(required = false) String accessCode,
                                                                    @RequestParam(required = false) String captchaToken){
        Integer appIdInternal = applicationId;
        if(appIdInternal == null){
            captchaClient.validateCaptcha(captchaToken, request.getRemoteHost(), "captchaToken");
            appIdInternal = commonApplicationService.getApplicationIdForDossierNumberAccessCode(dossierNumber, accessCode);
            if(appIdInternal == null) {
                throw new BadRequestException();
            }
        } else {
            AccessUtils.checkAccessAllowedForAppView(appIdInternal, commonApplicationService);
        }
        return getForAppId(appIdInternal);
    }

    private ApplicationCertificateDTO getForAppId(Integer applicationId){
        boolean isAccepted = commonApplicationService.applicationHasFoStatus(applicationId, FoApplicationStatus.ACCEPTED);
        if(isAccepted) {
            ApplicationCertificateDTO cert = boPublicServicesAdminService.getCertificateForApplication(applicationId);
            return cert;
        } else {
            return null;
        }
    }
}
