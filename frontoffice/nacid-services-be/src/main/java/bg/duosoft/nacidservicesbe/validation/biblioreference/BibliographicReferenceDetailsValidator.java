package bg.duosoft.nacidservicesbe.validation.biblioreference;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceDetailsDTO;
import bg.duosoft.nacidservicesbe.service.BibliographicReferenceService;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 14:10
 */
@Component
@RequiredArgsConstructor
public class BibliographicReferenceDetailsValidator implements Validator<BibliographicReferenceDetailsDTO> {

    private final BibliographicReferenceService bibliographicReferenceService;

    @Override
    public List<ValidationError> validate(BibliographicReferenceDetailsDTO details, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if(!Boolean.TRUE.equals(details.getForeignSearch()) && !Boolean.TRUE.equals(details.getNacidSearch())){
            reject(errors, "foreignSearch", ValidationMessageCodes.REQUIRED_CODE);
            reject(errors, "nacidSearch", ValidationMessageCodes.REQUIRED_CODE);
        }
        if(Boolean.TRUE.equals(details.getForeignSearch())){
            rejectIfEmpty(errors, details.getForeignSearchKind(), "foreignSearchKind", ValidationMessageCodes.REQUIRED_CODE);
        }
        if(Boolean.TRUE.equals(details.getNacidSearch())){
            rejectIfEmpty(errors, details.getNacidSearchKind(), "nacidSearchKind", ValidationMessageCodes.REQUIRED_CODE);
        }
        rejectIfEmptyString(errors, details.getTheme(), "theme", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, details.getKeywords(), "keywords", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, details.getSearchFrom(), "searchFrom", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyString(errors, details.getSearchTo(), "searchTo", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfEmptyCollection(errors, details.getSearchLanguages(), "searchLanguages", ValidationMessageCodes.SELECT_CODE);
        if(errors.size() == 0){
            Integer id = (Integer)args[0];
            BiblioReferenceApplicationDTO application = bibliographicReferenceService.getApplication(id);
            if(application.getSubmittedOrFinalized() && application.getBibliographicReferenceDetails() != null){
                BibliographicReferenceDetailsDTO dbDetails = application.getBibliographicReferenceDetails();
                if(!booleansAreNullOrEqual(dbDetails.getForeignSearch(), details.getForeignSearch())){
                    reject(errors, "foreignSearch", ValidationMessageCodes.INVALID_CODE);
                }
                if(!booleansAreNullOrEqual(dbDetails.getNacidSearch(), details.getNacidSearch())){
                    reject(errors, "nacidSearch", ValidationMessageCodes.INVALID_CODE);
                }
            }
        }
        return errors;
    }

    private boolean booleansAreNullOrEqual(Boolean b1, Boolean b2){
        return b1 == null && b2 == null || (b1 != null && b1.equals(b2));
    }
}
