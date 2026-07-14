package bg.duosoft.nacidservicesbe.evaluations;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
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
 * Date: 01.03.2023
 * Time: 14:19
 */
@Component
@RequiredArgsConstructor
public class BibliographicReferenceEvaluations implements BaseApplicationEvaluations<BiblioReferenceApplicationDTO> {

    private final DocTypeService docTypeService;

    @Override
    public List<EvaluationDTO> evaluateApplication(BiblioReferenceApplicationDTO application) {
        List<EvaluationDTO> evaluations = new ArrayList<>();

        CommonApplicationEvaluationsUtils.evaluateResultReceive(application, evaluations);

        evaluateBibliographicReferenceDetails(application, evaluations);

        CommonApplicationEvaluationsUtils.evaluateAttachedDocuments(application, evaluations, docTypeService.getApplicationDocTypeRequirements(application));

        return evaluations;
    }

    public void evaluateBibliographicReferenceDetails(BiblioReferenceApplicationDTO application, List<EvaluationDTO> evaluations){
        boolean hasLanguages = false;
        boolean hasSearchSelected = false;
        boolean themeEntered = false;

        if(application != null && application.getBibliographicReferenceDetails() != null){
            if(application.getBibliographicReferenceDetails().getSearchLanguages() != null && application.getBibliographicReferenceDetails().getSearchLanguages().size() >0) {
                hasLanguages = true;
            }
            if(Boolean.TRUE.equals(application.getBibliographicReferenceDetails().getForeignSearch()) || Boolean.TRUE.equals(application.getBibliographicReferenceDetails().getNacidSearch())){
                hasSearchSelected = true;
            }
            if(StringUtils.hasText(application.getBibliographicReferenceDetails().getTheme())){
                themeEntered = true;
            }
        }

        evaluations.add(new EvaluationDTO("rule.bibliographicReference.searchSelected", hasSearchSelected));
        evaluations.add(new EvaluationDTO("rule.bibliographicReference.theme", themeEntered));
        evaluations.add(new EvaluationDTO("rule.bibliographicReference.languagesSelected", hasLanguages));
    }
}
