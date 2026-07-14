package bg.duosoft.nacidservicesbe.validation.herecognition;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.HeRecognitionEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.06.2022
 * Time: 14:16
 */
@Component
@RequiredArgsConstructor
public class HeRecognitionFilingValidator implements ApplicationFilingValidator<HeRecognitionApplicationDTO> {

    private final HeRecognitionEvaluations heRecognitionEvaluations;

    @Override
    public List<ValidationError> validate(HeRecognitionApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = heRecognitionEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
