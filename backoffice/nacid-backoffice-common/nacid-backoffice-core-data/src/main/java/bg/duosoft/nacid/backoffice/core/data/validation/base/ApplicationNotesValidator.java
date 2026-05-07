package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.CollectionUtils;

import java.util.List;

public interface ApplicationNotesValidator extends DefaultValidation {

    default void validateApplicationNotes(List<ValidationError> errors, ApplicationDTO application) {
        List<ApplicationNotesDTO> applicationNotes = application.getApplicationNotes();
        if (!CollectionUtils.isEmpty(applicationNotes)) {
            for (int i = 0; i < applicationNotes.size(); i++) {
                ApplicationNotesDTO appNote = applicationNotes.get(i);
                rejectIfEmptyString(errors, appNote.getNote(), "applicationNotes." + i + ".note", "validation.field.required");
            }
        }
    }
}
