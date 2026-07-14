package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionApplicationDTO;
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
 * Time: 16:43
 */
@Component
@RequiredArgsConstructor
public class SuggestionEvaluations implements BaseApplicationEvaluations<SuggestionApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(SuggestionApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateSuggestionDetails(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateSuggestionDetails(SuggestionApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasSuggestion = false;
        if(application.getSuggestionDetails() != null && StringUtils.hasText(application.getSuggestionDetails().getSuggestion())){
            hasSuggestion = true;
        }
        evaluations.add(new EvaluationDTO("rule.suggestion.hasSuggestion", hasSuggestion));
    }
}
