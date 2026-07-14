package bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.*;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts.SarAcceptanceValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.AppCommissionMemberDataValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.AppCommissionMemberStatementDataValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts.SarMainDataValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts.SarReceptionValidator;
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
public class SarValidator implements Validator<RudiApplicationDTO> {

    private final SarMainDataValidator sarMainDataValidator;
    private final EducationDataValidator educationDataValidator;
    private final StatusDataValidator statusDataValidator;
    private final SarReceptionValidator sarReceptionValidator;
    private final SarAcceptanceValidator sarAcceptanceValidator;
    private final UniExamDataValidator uniExamDataValidator;
    private final ProgramExamDataValidator programExamDataValidator;
    private final TrainingLocationExamDataValidator trainingLocationExamDataValidator;
    private final DiplomaExamDataValidator diplomaExamDataValidator;

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationScope validationScope = (ValidationScope) args[0];
        switch (validationScope) {
            case FINAL -> execFinalScopeValidation(errors, obj);
            case MAIN_DATA -> sarMainDataValidator.validate(errors, obj);
            case EDUCATION_DATA -> educationDataValidator.validate(errors, obj);
            case STATUS_DATA -> statusDataValidator.validate(errors, obj);
            case RECEPTION -> sarReceptionValidator.validate(errors, obj);
            case E_APPS_ACCEPTANCE -> sarAcceptanceValidator.validate(errors, obj);
            case UNIVERSITY_EXAMINATION -> uniExamDataValidator.validate(errors, obj);
            case PROGRAM_EXAMINATION -> programExamDataValidator.validate(errors, obj);
            case TRAINING_LOCATION_EXAMINATION -> trainingLocationExamDataValidator.validate(errors, obj);
            case DIPLOMA_EXAMINATION -> diplomaExamDataValidator.validate(errors, obj);
        }

        return errors;
    }

    //TODO More
    private void execFinalScopeValidation(List<ValidationError> errors, RudiApplicationDTO obj) {
        sarMainDataValidator.validate(errors, obj);
        educationDataValidator.validate(errors, obj);
    }

}
