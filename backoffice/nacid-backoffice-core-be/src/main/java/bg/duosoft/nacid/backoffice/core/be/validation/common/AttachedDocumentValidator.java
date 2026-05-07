package bg.duosoft.nacid.backoffice.core.be.validation.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttachedDocumentValidator implements Validator<AttachedDocDTO>, bg.duosoft.nacid.backoffice.core.data.validation.base.AttachedDocumentValidator {

    @Override
    public List<ValidationError> validate(AttachedDocDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        validateAttachedDocument(errors, obj);
        return errors;
    }
    
}
