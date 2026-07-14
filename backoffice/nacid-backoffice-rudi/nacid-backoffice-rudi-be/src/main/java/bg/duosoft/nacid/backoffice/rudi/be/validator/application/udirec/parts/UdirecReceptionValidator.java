package bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiReceptionBaseValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UdirecReceptionValidator extends RudiReceptionBaseValidator implements Validator<RudiApplicationDTO> {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = super.validate(rudiApplicationDTO);

        ApplicationDTO application = rudiApplicationDTO.getApplication();
        rejectIfEmpty(errors, application.getPersonalDocumentType(), "personalDocumentTypeId", "validation.field.required");

        validateCertificateReceiveMethod(application, errors);
        return errors;
    }

}
