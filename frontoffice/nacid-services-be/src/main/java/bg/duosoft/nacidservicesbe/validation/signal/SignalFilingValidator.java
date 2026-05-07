package bg.duosoft.nacidservicesbe.validation.signal;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.SignalEvaluations;
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
public class SignalFilingValidator implements ApplicationFilingValidator<SignalApplicationDTO> {

    private final SignalEvaluations signalEvaluations;

    @Override
    public List<ValidationError> validate(SignalApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = signalEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
