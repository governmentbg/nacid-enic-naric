package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.evaluations.utils.RudiEducationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 18:35
 */
@Component
@RequiredArgsConstructor
public class DocDegreeEvaluations implements BaseApplicationEvaluations<DocDegreesApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(DocDegreesApplicationDTO application) {

        List<EvaluationDTO> evaluations = new ArrayList<>();
        CommonApplicationEvaluationsUtils.evaluateDeclarations(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateCertificateReceiveForm(application, evaluations);

        RudiEducationEvaluationsUtils.evaluateUniversities(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateEducationPlaces(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateRecognitionCategory(getEducationDetails(application), evaluations);
        RudiEducationEvaluationsUtils.evaluateOriginalGainedLevel(getEducationDetails(application), evaluations);
        if(getEducationDetails(application) != null && getEducationDetails(application).getRecognitionCategory() != null && "DOC".equals(getEducationDetails(application).getRecognitionCategory().getId())) {
            RudiEducationEvaluationsUtils.evaluateEducationStartEnd(getEducationDetails(application), evaluations);
        }
        evaluateGraduationWay(getEducationDetails(application), evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateGraduationWay(DocEducationDetailsDTO education, List<EvaluationDTO> evaluations){
        boolean gradWay = false;

        if(education != null && education.getGraduationWay() != null && !education.getGraduationWay().isEmpty()){
            long badGradWay = education.getGraduationWay().stream().filter(grad -> !StringUtils.hasText(grad.getId())).count();
            gradWay = badGradWay == 0;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.graduationWay", gradWay));
    }

    private DocEducationDetailsDTO getEducationDetails(DocDegreesApplicationDTO application){
        return application == null ? null: application.getEducationDetails();
    }
}
