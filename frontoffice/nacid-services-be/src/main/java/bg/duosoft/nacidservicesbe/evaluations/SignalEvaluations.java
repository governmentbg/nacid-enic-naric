package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 17:04
 */
@Component
@RequiredArgsConstructor
public class SignalEvaluations implements BaseApplicationEvaluations<SignalApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(SignalApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateSignalDetails(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateSignalDetails(SignalApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasViolationDescription = false;
        boolean hasViolationPlace = false;
        if(application.getSignalDetails() != null){
            hasViolationDescription = StringUtils.hasText(application.getSignalDetails().getViolationDescription());
            hasViolationPlace = StringUtils.hasText(application.getSignalDetails().getViolationPlace());
        }
        evaluations.add(new EvaluationDTO("rule.signal.hasViolationDescription", hasViolationDescription));
        evaluations.add(new EvaluationDTO("rule.signal.hasViolationPlace", hasViolationPlace));
    }
}
