package bg.duosoft.nacidservicesbe.validation.common.note;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationNoteValidator implements Validator<ApplicationNoteDTO> {

    @Override
    public List<ValidationError> validate(ApplicationNoteDTO applicationNote, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmpty(errors, applicationNote.getApplicationId(), "applicationId", "validation.field.required");
        rejectIfEmpty(errors, applicationNote.getNoteText(), "noteText", "validation.field.required");
        return errors;
    }
}
