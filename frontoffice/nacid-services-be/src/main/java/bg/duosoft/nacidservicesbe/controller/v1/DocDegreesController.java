package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.controller.utils.EducationDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.DocDegreesService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.common.applicantdetails.RudiApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.docdegrees.DocDegreesFilingValidator;
import bg.duosoft.nacidservicesbe.validation.docdegrees.DocEducationDetailsValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.07.2022
 * Time: 11:17
 */
@Api(tags = Tags.DOC_DEGREES)
@RestController
@RequestMapping("/api/v1/doc-degrees")
@RequiredArgsConstructor
public class DocDegreesController extends BaseApplicationController<DocDegreesApplicationDTO, RudiApplicantDetailsDTO, DocEducationDetailsDTO> {

    private final DocDegreesService docDegreesService;
    private final RudiApplicantDetailsValidator rudiApplicantDetailsValidator;
    private final DocEducationDetailsValidator docEducationDetailsValidator;
    private final DocDegreesFilingValidator docDegreesFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return docDegreesService;
    }

    @Override
    public Validator<RudiApplicantDetailsDTO> getApplicantDetailsValidator() {
        return rudiApplicantDetailsValidator;
    }

    @Override
    public Validator<DocEducationDetailsDTO> getApplicationSpecificDetailsValidator() {
        return docEducationDetailsValidator;
    }

    @Override
    public Validator<DocDegreesApplicationDTO> getFilingValidator() {
        return docDegreesFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(RudiApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveRudiApplicantDetails(applicantDetails, getBoPublicServicesService());
    }

    @Override
    public void preSaveRequestSpecificDetails(DocEducationDetailsDTO specificDetails) {
        preSaveDocEducationDetails(specificDetails);
    }

    private void preSaveDocEducationDetails(DocEducationDetailsDTO educationDetails){
        EducationDetailsDTOUtils.preSaveEducationDetails(educationDetails);

        if(educationDetails.getGainedLevelProfGroup() != null && educationDetails.getGainedLevelProfGroup().getId() == null){
            educationDetails.setGainedLevelProfGroup(null);
        }
        if(educationDetails.getDissertationLanguage() != null && !StringUtils.hasText(educationDetails.getDissertationLanguage().getId())){
            educationDetails.setDissertationLanguage(null);
        }
        if(educationDetails.getPreviousUniversityDiploma() != null && educationDetails.getPreviousUniversityDiploma().getGainedLevel() != null && !StringUtils.hasText(educationDetails.getPreviousUniversityDiploma().getGainedLevel().getId())){
            educationDetails.getPreviousUniversityDiploma().setGainedLevel(null);
        }
    }
}
