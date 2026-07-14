package bg.duosoft.nacidservicesbe.validation.inquiry;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.InquiryEvaluations;
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
public class InquiryFilingValidator implements ApplicationFilingValidator<InquiryApplicationDTO> {

    private final InquiryEvaluations inquiryEvaluations;

    @Override
    public List<ValidationError> validate(InquiryApplicationDTO application, Object... args) {
        List<EvaluationDTO> evaluations = inquiryEvaluations.evaluateApplication(application);
        return getFilingErrors(evaluations);
    }
}
