package bg.duosoft.nacidservicesbe.validation.biblioreference;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidservicesbe.evaluations.BibliographicReferenceEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 14:10
 */
@Component
@RequiredArgsConstructor
public class BibliographicReferenceFilingValidator implements ApplicationFilingValidator<BiblioReferenceApplicationDTO> {

    private final BibliographicReferenceEvaluations bibliographicReferenceEvaluations;

    @Override
    public List<ValidationError> validate(BiblioReferenceApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = bibliographicReferenceEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
