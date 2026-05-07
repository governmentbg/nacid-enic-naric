package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
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
public class UniversityExaminationValidator implements Validator<TrainingCourseUniversityExaminationDTO> {

    @Override
    public List<ValidationError> validate(TrainingCourseUniversityExaminationDTO universityExamination, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        rejectIfEmpty(errors, universityExamination.getExaminationDate(), "examinationDate", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(universityExamination.getUniversity()) || Objects.isNull(universityExamination.getUniversity().getId()), "university", "validation.field.required");
        rejectIfEmptyString(errors, universityExamination.getUserCreated(), "userCreated", "validation.field.required");
        rejectIfEmptyBoolean(errors, universityExamination.getIsCommunicated(), "isCommunicated", "validation.field.required");
        rejectIfEmptyBoolean(errors, universityExamination.getIsRecognized(), "isRecognized", "validation.field.required");
        rejectIfEmptyBoolean(errors, universityExamination.getIsJointDegree(), "isJointDegree", "validation.field.required");

        return errors;
    }
}
