package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.controller.utils.EducationDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.HeRecognitionService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.common.applicantdetails.RudiApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.herecognition.HeEducationDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.herecognition.HeRecognitionFilingValidator;
import bg.duosoft.nacidshareddata.util.ReferenceDataConstants;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.05.2022
 * Time: 15:12
 */
@Api(tags = Tags.HE_RECOGNITION)
@RestController
@RequestMapping("/api/v1/he-recognition")
@RequiredArgsConstructor
public class HeRecognitionController extends BaseApplicationController<HeRecognitionApplicationDTO, RudiApplicantDetailsDTO, HeEducationDetailsDTO> {

    private final HeRecognitionService heRecognitionService;
    private final RudiApplicantDetailsValidator rudiApplicantDetailsValidator;
    private final HeEducationDetailsValidator heEducationDetailsValidator;
    private final HeRecognitionFilingValidator heRecognitionFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return heRecognitionService;
    }

    @Override
    public Validator<RudiApplicantDetailsDTO> getApplicantDetailsValidator() {
        return rudiApplicantDetailsValidator;
    }

    @Override
    public Validator<HeEducationDetailsDTO> getApplicationSpecificDetailsValidator() {
        return heEducationDetailsValidator;
    }

    @Override
    public Validator<HeRecognitionApplicationDTO> getFilingValidator() {
        return heRecognitionFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(RudiApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveRudiApplicantDetails(applicantDetails, getBoPublicServicesService());
    }

    @Override
    public void preSaveRequestSpecificDetails(HeEducationDetailsDTO specificDetails) {
        preSaveHeEducationDetails(specificDetails);
    }

    private void preSaveHeEducationDetails(HeEducationDetailsDTO educationDetails){
        EducationDetailsDTOUtils.preSaveEducationDetails(educationDetails);
        EducationDetailsDTOUtils.preSaveEducationDetailsSpecialities(educationDetails);
        if(educationDetails.getRecognitionAim() != null){
            ReferenceDataDTO other = educationDetails.getRecognitionAim().stream().filter(ra -> ReferenceDataConstants.OTHER_VALUE.equals(ra.getId())).findFirst().orElse(null);
            if(other == null){
                educationDetails.setRecognitionAimOtherDetails(null);
            }
            educationDetails.setRecognitionAim(educationDetails.getRecognitionAim().stream().filter(ra -> StringUtils.hasText(ra.getId())).collect(Collectors.toList()));
        }
        if(educationDetails.getPreviousUniversityDiploma() != null && educationDetails.getPreviousUniversityDiploma().getGainedLevel() != null && !StringUtils.hasText(educationDetails.getPreviousUniversityDiploma().getGainedLevel().getId())){
            educationDetails.getPreviousUniversityDiploma().setGainedLevel(null);
        }
    }
}
