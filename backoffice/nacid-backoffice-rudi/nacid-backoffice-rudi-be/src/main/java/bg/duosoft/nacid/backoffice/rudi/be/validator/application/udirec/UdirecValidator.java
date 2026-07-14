package bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.*;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec.parts.UdirecAcceptanceValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec.parts.UdirecReceptionValidator;
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
public class UdirecValidator implements Validator<RudiApplicationDTO> {

    private final EducationDataValidator educationDataValidator;
    private final MainDataBaseValidator mainDataBaseValidator;
    private final StatusDataValidator statusDataValidator;
    private final UniExamDataValidator uniExamDataValidator;
    private final ProgramExamDataValidator programExamDataValidator;
    private final TrainingLocationExamDataValidator trainingLocationExamDataValidator;
    private final DiplomaExamDataValidator diplomaExamDataValidator;
    private final UdirecReceptionValidator receptionValidator;
    private final UdirecAcceptanceValidator udirecAcceptanceValidator;

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationScope validationScope = (ValidationScope) args[0];
        switch (validationScope) {
            case FINAL -> execFinalScopeValidation(errors, obj);
            case MAIN_DATA -> mainDataBaseValidator.validate(errors, obj);
            case EDUCATION_DATA -> educationDataValidator.validate(errors, obj);
            case STATUS_DATA -> statusDataValidator.validate(errors, obj);
            case UNIVERSITY_EXAMINATION -> uniExamDataValidator.validate(errors, obj);
            case PROGRAM_EXAMINATION -> programExamDataValidator.validate(errors, obj);
            case TRAINING_LOCATION_EXAMINATION -> trainingLocationExamDataValidator.validate(errors, obj);
            case DIPLOMA_EXAMINATION -> diplomaExamDataValidator.validate(errors, obj);
            case RECEPTION -> receptionValidator.validate(errors, obj);
            case E_APPS_ACCEPTANCE -> udirecAcceptanceValidator.validate(errors, obj);
        }

        return errors;
    }

    private void execFinalScopeValidation(List<ValidationError> errors, RudiApplicationDTO obj) {
        educationDataValidator.validate(errors, obj);
        mainDataBaseValidator.validate(errors, obj);
    }

}
