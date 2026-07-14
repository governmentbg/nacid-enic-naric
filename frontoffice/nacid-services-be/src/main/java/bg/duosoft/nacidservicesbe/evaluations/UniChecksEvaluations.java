package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.evaluations.utils.RudiEducationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.evaluations.utils.WithSpecialitiesEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 17:09
 */
@Component
@RequiredArgsConstructor
public class UniChecksEvaluations implements BaseApplicationEvaluations<UniChecksApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(UniChecksApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();
        CommonApplicationEvaluationsUtils.evaluateDeclarations(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateUniChecksServiceSelected(getEducationDetails(application), evaluations);
        evaluateDiplomaHolder(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateUniversities(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateEducationPlaces(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateRecognitionCategory(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateOriginalGainedLevel(getEducationDetails(application), evaluations);
        WithSpecialitiesEvaluationsUtils.evaluateSpecialities(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateEducationStartEnd(getEducationDetails(application), evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateUniChecksServiceSelected(UniChecksEducationDetailsDTO education, List<EvaluationDTO> evaluations){
        boolean hasSARSelected = false;
        boolean hasServiceType = false;

        if(education != null){
            hasSARSelected = Boolean.TRUE.equals(education.getStatute()) || Boolean.TRUE.equals(education.getAuthenticity()) || Boolean.TRUE.equals(education.getRecommendation());
            hasServiceType = education.getServiceType() != null && StringUtils.hasText(education.getServiceType().getId());
        }
        evaluations.add(new EvaluationDTO("rule.educationDetails.sarSelected", hasSARSelected));
        evaluations.add(new EvaluationDTO("rule.educationDetails.serviceType", hasServiceType));
    }

    public void evaluateDiplomaHolder(UniChecksEducationDetailsDTO education, List<EvaluationDTO> evaluations){
        boolean diplomaHolder = false;
        if(education != null && education.getDiplomaHolder() != null){
            diplomaHolder = StringUtils.hasText(education.getDiplomaHolder().getPersonalId()) || StringUtils.hasText(education.getDiplomaHolder().getPersonalNacidId());
        }
        evaluations.add(new EvaluationDTO("rule.educationDetails.diplomaHolder", diplomaHolder));
    }

    private UniChecksEducationDetailsDTO getEducationDetails(UniChecksApplicationDTO application){
        return application == null ? null: application.getEducationDetails();
    }
}
