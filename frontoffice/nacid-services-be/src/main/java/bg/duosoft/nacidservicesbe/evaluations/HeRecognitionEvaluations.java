package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.evaluations.utils.RudiEducationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.evaluations.utils.WithSpecialitiesEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 15:56
 */
@Component
@RequiredArgsConstructor
public class HeRecognitionEvaluations implements BaseApplicationEvaluations<HeRecognitionApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(HeRecognitionApplicationDTO application) {

        List<EvaluationDTO> evaluations = new ArrayList<>();
        CommonApplicationEvaluationsUtils.evaluateDeclarations(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateCertificateReceiveForm(application, evaluations);

        RudiEducationEvaluationsUtils.evaluateUniversities(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateEducationPlaces(getEducationDetails(application), evaluations);
        WithSpecialitiesEvaluationsUtils.evaluateSpecialities(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateOriginalGainedLevel(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateEducationStartEnd(getEducationDetails(application), evaluations);
        evaluateRecognitionAim(getEducationDetails(application), evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateRecognitionAim(HeEducationDetailsDTO educationDetails, List<EvaluationDTO> evaluations) {
        boolean recognitionAim = false;
        if(educationDetails != null && educationDetails.getRecognitionAim() != null && educationDetails.getRecognitionAim().size() >0){
            recognitionAim = true;
        }
        evaluations.add(new EvaluationDTO("rule.educationDetails.recognitionAim", recognitionAim));
    }

    private HeEducationDetailsDTO getEducationDetails(HeRecognitionApplicationDTO application){
        return application == null ? null: application.getEducationDetails();
    }
}
