package bg.duosoft.nacidservicesbe.validation.unichecks;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.UniChecksEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 17:16
 */
@Component
@RequiredArgsConstructor
public class UniChecksFilingValidator  implements ApplicationFilingValidator<UniChecksApplicationDTO> {

    private final UniChecksEvaluations uniChecksEvaluations;

    @Override
    public List<ValidationError> validate(UniChecksApplicationDTO application, Object... objects) {
        List<EvaluationDTO> evaluations = uniChecksEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
