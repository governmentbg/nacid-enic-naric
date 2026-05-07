package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:00
 */
@Component
@RequiredArgsConstructor
public class InquiryEvaluations implements BaseApplicationEvaluations<InquiryApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(InquiryApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateInquiryKinds(application, evaluations);
        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateInquiryKinds(InquiryApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasKinds = false;
        if(application != null && application.getInquiryDetails() != null && application.getInquiryDetails().getInquiryKinds() != null && application.getInquiryDetails().getInquiryKinds().size() >0){
            hasKinds = true;
        }

        evaluations.add(new EvaluationDTO("rule.inquiry.kindsSelected", hasKinds));
    }
}
