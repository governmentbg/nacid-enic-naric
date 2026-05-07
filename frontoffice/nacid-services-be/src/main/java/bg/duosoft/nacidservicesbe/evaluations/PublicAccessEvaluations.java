package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessApplicationDTO;
import bg.duosoft.nacidservicesbe.evaluations.utils.CommonApplicationEvaluationsUtils;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:48
 */
@Component
@RequiredArgsConstructor
public class PublicAccessEvaluations implements  BaseApplicationEvaluations<PublicAccessApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(PublicAccessApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluatePublicAccessDetails(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluatePublicAccessDetails(PublicAccessApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasAbout = false;
        if(application.getPublicAccessDetails() != null && StringUtils.hasText(application.getPublicAccessDetails().getAbout())){
            hasAbout = true;
        }
        evaluations.add(new EvaluationDTO("rule.publicAccess.hasAbout", hasAbout));
    }
}
