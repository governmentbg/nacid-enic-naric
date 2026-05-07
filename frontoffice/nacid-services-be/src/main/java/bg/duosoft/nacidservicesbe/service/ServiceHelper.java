package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2023
 * Time: 11:41
 */
@Component
@RequiredArgsConstructor
public class ServiceHelper {

    private final HeRecognitionService heRecognitionService;
    private final DocDegreesService docDegreesService;
    private final UniChecksService uniChecksService;
    private final RegprofService regprofService;
    private final OfficialNotesService officialNotesService;
    private final InquiryService inquiryService;
    private final BibliographicReferenceService bibliographicReferenceService;
    private final DocDeliveryService docDeliveryService;
    private final SuggestionService suggestionService;
    private final SignalService signalService;
    private final PublicAccessService publicAccessService;
    @Getter
    private final RegprofApostilleService regprofApostilleService;

    private final CommonApplicationService commonApplicationService;

    public BaseApplicationService getSpecificApplicationService(Integer id) {
        ApplicationSubtype subtype = commonApplicationService.getApplicationSubtype(id);
        return switch (subtype) {
            case HE_RECOGNITION -> heRecognitionService;
            case DOC_DEGREES -> docDegreesService;
            case UNI_CHECKS -> uniChecksService;
            case REGULATED_PROFESSIONS -> regprofService;
            case OFFICIAL_NOTE -> officialNotesService;
            case INQUIRY -> inquiryService;
            case BIBLIO_REFERENCE -> bibliographicReferenceService;
            case DOCUMENT_SERVICE -> docDeliveryService;
            case SUGGESTION -> suggestionService;
            case SIGNAL -> signalService;
            case PUBLIC_ACCESS -> publicAccessService;
            default -> throw new RuntimeException("No service for subtype " + subtype);
        };
    }

}
