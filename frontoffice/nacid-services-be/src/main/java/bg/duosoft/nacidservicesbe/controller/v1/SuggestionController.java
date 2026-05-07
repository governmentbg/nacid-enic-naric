package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.SuggestionService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.suggestion.SuggestionApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.suggestion.SuggestionDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.suggestion.SuggestionFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 16:59
 */
@Api(tags = Tags.SUGGESTION)
@RestController
@RequestMapping("/api/v1/suggestion")
@RequiredArgsConstructor
public class SuggestionController extends BaseApplicationController<SuggestionApplicationDTO, CommonApplicantDetailsDTO, SuggestionDetailsDTO>{

    private final SuggestionService suggestionService;
    private final SuggestionApplicantDetailsValidator suggestionApplicantDetailsValidator;
    private final SuggestionDetailsValidator suggestionDetailsValidator;
    private final SuggestionFilingValidator suggestionFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return suggestionService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return suggestionApplicantDetailsValidator;
    }

    @Override
    public Validator<SuggestionDetailsDTO> getApplicationSpecificDetailsValidator() {
        return suggestionDetailsValidator;
    }

    @Override
    public Validator<SuggestionApplicationDTO> getFilingValidator() {
        return suggestionFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
