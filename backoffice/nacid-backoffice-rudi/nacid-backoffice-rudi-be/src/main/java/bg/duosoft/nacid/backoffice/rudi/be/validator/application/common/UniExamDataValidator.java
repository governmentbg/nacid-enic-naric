package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.validation.base.AttachedDocumentValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniExamDataValidator implements Validator<RudiApplicationDTO>, AttachedDocumentValidator {
    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        TrainingCourseDTO trainingCourse = obj.getTrainingCourse();
        rejectIfEmpty(errors, trainingCourse, "trainingCourse", "validation.field.required");

        if (Objects.nonNull(trainingCourse)) {
            List<TrainingCourseUniversityExaminationDTO> courseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();
            rejectIfEmptyCollection(errors, courseUniversityExaminations, "courseUniExaminations", "validation.field.required");

            if (!CollectionUtils.isEmpty(courseUniversityExaminations)) {
                for (TrainingCourseUniversityExaminationDTO examination : courseUniversityExaminations) {
                    rejectIfEmpty(errors, examination, "uniExamination", "validation.field.required");

                    if (Objects.nonNull(examination)) {
                        rejectIfTrue(errors, Objects.isNull(examination.getUniversity()) || Objects.isNull(examination.getUniversity().getId()), "university", "validation.field.required");
                        rejectIfEmptyString(errors, examination.getUserCreated(), "uniExaminationUserCreated", "validation.field.required");
                        rejectIfEmpty(errors, examination.getExaminationDate(), "examinationDate", "validation.field.required");
                        rejectIfEmpty(errors, examination.getIsCommunicated(), "isCommunicated", "validation.field.required");
                        rejectIfEmpty(errors, examination.getIsRecognized(), "isRecognized", "validation.field.required");
                        rejectIfEmpty(errors, examination.getIsJointDegree(), "isJointDegree", "validation.field.required");

                        if (StringUtils.hasText(examination.getNotes())) {
                            rejectIfTrue(errors, examination.getNotes().length() > MAX_INPUT_LENGTH_XXL, "notes", "validation.charCount.invalid.10000");
                        }

                        validateAttachedDocuments(errors, examination.getAttachedDocs());
                    }
                }
            }
        }

        return errors;
    }
}
