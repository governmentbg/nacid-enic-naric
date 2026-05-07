package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.SignalService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.signal.SignalApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.signal.SignalDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.signal.SignalFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 17:29
 */
@Api(tags = Tags.SIGNAL)
@RestController
@RequestMapping("/api/v1/signal")
@RequiredArgsConstructor
public class SignalController extends BaseApplicationController<SignalApplicationDTO, CommonApplicantDetailsDTO, SignalDetailsDTO>{

    private final SignalService signalService;
    private final SignalApplicantDetailsValidator signalApplicantDetailsValidator;
    private final SignalDetailsValidator signalDetailsValidator;
    private final SignalFilingValidator signalFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return signalService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return signalApplicantDetailsValidator;
    }

    @Override
    public Validator<SignalDetailsDTO> getApplicationSpecificDetailsValidator() {
        return signalDetailsValidator;
    }

    @Override
    public Validator<SignalApplicationDTO> getFilingValidator() {
        return signalFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
