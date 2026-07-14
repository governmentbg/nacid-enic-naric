package bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UdirecAcceptanceValidator implements Validator<RudiApplicationDTO> {

    private final UdirecReceptionValidator udirecReceptionValidator;

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = udirecReceptionValidator.validate(rudiApplicationDTO);
        removeRedundantReceptionErrors(errors);
        return errors;
    }

    private static void removeRedundantReceptionErrors(List<ValidationError> errors) {
        if (!CollectionUtils.isEmpty(errors)) {
            errors.removeIf(e -> e.getPointer().equals("certificateReceiveMethod"));
        }
    }

}
