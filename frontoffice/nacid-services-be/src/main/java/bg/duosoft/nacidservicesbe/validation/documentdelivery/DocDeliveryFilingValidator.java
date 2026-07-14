package bg.duosoft.nacidservicesbe.validation.documentdelivery;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.DocDeliveryEvaluations;
import bg.duosoft.nacidservicesbe.validation.common.filing.ApplicationFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 13:13
 */
@Component
@RequiredArgsConstructor
public class DocDeliveryFilingValidator implements ApplicationFilingValidator<DocDeliveryApplicationDTO> {

    private final DocDeliveryEvaluations docDeliveryEvaluations;

    @Override
    public List<ValidationError> validate(DocDeliveryApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = docDeliveryEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
