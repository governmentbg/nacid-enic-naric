package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
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
 * Date: 27.02.2023
 * Time: 16:00
 */
@Component
@RequiredArgsConstructor
public class OfficialNotesEvaluations implements BaseApplicationEvaluations<OfficialNotesApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(OfficialNotesApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateOfficialNoteKinds(application, evaluations);
        evaluateServiceType(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateOfficialNoteKinds(OfficialNotesApplicationDTO application, List<EvaluationDTO> evaluations) {
        boolean hasKinds = false;
        if (application != null && application.getOfficialNotesDetails() != null && application.getOfficialNotesDetails().getOfficialNotesKinds() != null && application.getOfficialNotesDetails().getOfficialNotesKinds().size() > 0) {
            hasKinds = true;
        }

        evaluations.add(new EvaluationDTO("rule.officialNoteDetails.kindsSelected", hasKinds));
    }

    public void evaluateServiceType(OfficialNotesApplicationDTO application, List<EvaluationDTO> evaluations) {
        boolean serviceType = false;
        if (application != null && application.getOfficialNotesDetails() != null && application.getOfficialNotesDetails().getServiceType() != null && StringUtils.hasText(application.getOfficialNotesDetails().getServiceType().getId())) {
            serviceType = true;
        }

        evaluations.add(new EvaluationDTO("rule.officialNoteDetails.serviceType", serviceType));
    }
}
