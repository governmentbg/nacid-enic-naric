package bg.duosoft.nacidservicesbe.validation.publicaccess;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.PublicAccessEvaluations;
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
public class PublicAccessFilingValidator implements ApplicationFilingValidator<PublicAccessApplicationDTO> {

    private final PublicAccessEvaluations publicAccessEvaluations;

    @Override
    public List<ValidationError> validate(PublicAccessApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = publicAccessEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
