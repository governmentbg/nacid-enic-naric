package bg.duosoft.nacidservicesbe.validation.docdegrees;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.DocDegreeEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.07.2022
 * Time: 15:37
 */
@Component
@RequiredArgsConstructor
public class DocDegreesFilingValidator implements ApplicationFilingValidator<DocDegreesApplicationDTO> {

    private final DocDegreeEvaluations docDegreeEvaluations;

    @Override
    public List<ValidationError> validate(DocDegreesApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = docDegreeEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
