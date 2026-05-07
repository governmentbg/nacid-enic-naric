package bg.duosoft.nacidservicesbe.validation.regprof;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.RegprofEvaluations;
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
public class RegprofFilingValidator implements ApplicationFilingValidator<RegprofApplicationDTO> {

    private final RegprofEvaluations regprofEvaluations;

    @Override
    public List<ValidationError> validate(RegprofApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = regprofEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
