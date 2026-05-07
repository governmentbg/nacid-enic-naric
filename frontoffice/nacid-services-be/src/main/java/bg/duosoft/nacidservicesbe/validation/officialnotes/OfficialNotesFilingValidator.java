package bg.duosoft.nacidservicesbe.validation.officialnotes;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.OfficialNotesEvaluations;
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
public class OfficialNotesFilingValidator  implements ApplicationFilingValidator<OfficialNotesApplicationDTO> {

    private final OfficialNotesEvaluations officialNotesEvaluations;

    @Override
    public List<ValidationError> validate(OfficialNotesApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = officialNotesEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
