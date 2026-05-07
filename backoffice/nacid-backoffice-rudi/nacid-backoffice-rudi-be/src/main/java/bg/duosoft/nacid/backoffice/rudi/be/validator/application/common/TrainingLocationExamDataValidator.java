package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingLocationExaminationDTO;
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
public class TrainingLocationExamDataValidator implements Validator<RudiApplicationDTO> {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        TrainingCourseDTO trainingCourse = obj.getTrainingCourse();
        rejectIfEmpty(errors, trainingCourse, "trainingCourse", "validation.field.required");

        if (Objects.nonNull(trainingCourse)) {
            TrainingLocationExaminationDTO trainingLocationExamination = trainingCourse.getTrainingLocationExamination();
            rejectIfEmpty(errors, trainingLocationExamination, "trainingLocationExamination", "validation.field.required");

            if (Objects.nonNull(trainingLocationExamination)) {
                rejectIfEmptyBoolean(errors, trainingLocationExamination.getIsLegitimate(), "isLegitimate","validation.field.required");
            }
        }

        return errors;
    }
}
