package bg.duosoft.nacidservicesbe.validation.officialnotes;

import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesDetailsDTO;
import bg.duosoft.nacidservicesbe.service.OfficialNotesService;
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
 * Date: 27.02.2023
 * Time: 16:36
 */
@Component
@RequiredArgsConstructor
public class OfficialNotesDetailsValidator implements Validator<OfficialNotesDetailsDTO> {

    private final OfficialNotesService officialNotesService;

    @Override
    public List<ValidationError> validate(OfficialNotesDetailsDTO details, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmptyCollection(errors, details.getOfficialNotesKinds(), "officialNotesKinds", ValidationMessageCodes.SELECT_CODE);
        if (details.getOfficialNotesKinds() != null && details.getOfficialNotesKinds().stream().filter(kd -> OfficialNoteKind.PAPER_NOTE.equals(kd) || OfficialNoteKind.PROJECT_NOTE.equals(kd)).count() > 0) {
            reject(errors, "officialNotesKinds", ValidationMessageCodes.INVALID_CODE);
        }
        rejectIfEmptyString(errors, details.getServiceType() != null ? details.getServiceType().getId() : null, "serviceType.id", ValidationMessageCodes.REQUIRED_CODE);

        if (errors.size() == 0) {
            Integer id = (Integer) args[0];
            OfficialNotesApplicationDTO application = officialNotesService.getApplication(id);
            if (application.getSubmittedOrFinalized() && application.getOfficialNotesDetails() != null && application.getOfficialNotesDetails().getOfficialNotesKinds() != null) {
                List<OfficialNoteKind> dbKinds = application.getOfficialNotesDetails().getOfficialNotesKinds();
                if (!dbKinds.containsAll(details.getOfficialNotesKinds())) {
                    reject(errors, "officialNotesKinds", ValidationMessageCodes.INVALID_CODE);
                } else if (!details.getOfficialNotesKinds().containsAll(dbKinds)) {
                    reject(errors, "officialNotesKinds", ValidationMessageCodes.INVALID_CODE);
                }
            }
        }
        return errors;
    }
}
