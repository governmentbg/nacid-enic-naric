package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDiplomaExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.base.AttachedDocumentValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiplomaExamDataValidator implements Validator<RudiApplicationDTO>, AttachedDocumentValidator {
    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        TrainingCourseDTO trainingCourse = obj.getTrainingCourse();
        rejectIfEmpty(errors, trainingCourse, "trainingCourse", "validation.field.required");

        if (Objects.nonNull(trainingCourse)) {
            TrainingCourseDiplomaExaminationDTO diplomaExamination = trainingCourse.getDiplomaExamination();
            rejectIfEmpty(errors, diplomaExamination, "diplomaExamination", "validation.field.required");

            if (Objects.nonNull(diplomaExamination)) {
                rejectIfEmpty(errors, diplomaExamination.getExaminationDate(), "examinationDate", "validation.field.required");
                rejectIfEmptyBoolean(errors, diplomaExamination.getIsAuthentic(), "isAuthentic", "validation.field.required");
                rejectIfEmptyBoolean(errors, diplomaExamination.getIsFoundInRegister(), "isFoundInRegister", "validation.field.required");
                rejectIfEmptyBoolean(errors, diplomaExamination.getIsInstitutionCommunicated(), "isInstitutionCommunicated", "validation.field.required");
                rejectIfEmptyBoolean(errors, diplomaExamination.getIsUniversityCommunicated(), "isUniversityCommunicated", "validation.field.required");
                rejectIfEmptyBoolean(errors, diplomaExamination.getIsStateApproved(), "isStateApproved", "validation.field.required");

                if (StringUtils.hasText(diplomaExamination.getNotes())) {
                    rejectIfTrue(errors, diplomaExamination.getNotes().length() > MAX_INPUT_LENGTH_XXL, "notes", "validation.charCount.invalid.10000");
                }

                validateAttachedDocuments(errors, diplomaExamination.getAttachedDocs());
            }
        }

        return errors;
    }
}
