package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 12:20
 */
@Component
@RequiredArgsConstructor
public class DocDeliveryEvaluations implements BaseApplicationEvaluations<DocDeliveryApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(DocDeliveryApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateDocDeliveryDetails(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateDocDeliveryDetails(DocDeliveryApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasBibliographicDetails = false;

        if(application.getBibliographicDetails() != null && application.getBibliographicDetails().getEntries() != null && application.getBibliographicDetails().getEntries().size()>0){
            hasBibliographicDetails = true;
        }

        evaluations.add(new EvaluationDTO("rule.docDelivery.hasBibliographicDetails", hasBibliographicDetails));
    }
}
