package bg.duosoft.nacidservicesbe.validation.suggestion;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.SuggestionEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:40
 */
@Component
@RequiredArgsConstructor
public class SuggestionFilingValidator implements ApplicationFilingValidator<SuggestionApplicationDTO> {

    private final SuggestionEvaluations suggestionEvaluations;

    @Override
    public List<ValidationError> validate(SuggestionApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = suggestionEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
