package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProgramExamDataValidator implements Validator<RudiApplicationDTO> {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        TrainingCourseDTO trainingCourse = obj.getTrainingCourse();
        rejectIfEmpty(errors, trainingCourse, "trainingCourse", "validation.field.required");

        if (Objects.nonNull(trainingCourse)) {
            TrainingCourseProgramExaminationDTO programExamination = trainingCourse.getProgramExamination();
            rejectIfEmpty(errors, programExamination, "programExamination", "validation.field.required");

            if (Objects.nonNull(programExamination)) {
                rejectIfEmptyBoolean(errors, programExamination.getIsLegitimate(), "isLegitimate", "validation.field.required");

                rejectIfEmpty(errors, programExamination.getProgramType(), "programTypeId", "validation.field.required");
                if(Objects.nonNull(programExamination.getProgramType())) {
                    rejectIfEmptyString(errors, programExamination.getProgramType().getId(), "programTypeId", "validation.field.required");
                }
            }
        }

        return errors;
    }
}
