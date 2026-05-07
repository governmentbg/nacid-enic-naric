package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaSubjectDto;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DiplomaSubjetsValidator implements Validator<List<DiplomaSubjectDto>> {
    @Override
    public List<ValidationError> validate(List<DiplomaSubjectDto> obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(obj), "diplomaSubjects", "diplomaSubjects.isNull");
        rejectIfEmpty(errors, obj, "diplomaSubjects", "diplomaSubjects.empty");
        if (Objects.nonNull(obj) && !obj.isEmpty()) {
            obj.forEach(subject -> {
                rejectIfTrue(errors, Objects.isNull(subject), "subject", "diplomaSubjects.subject.empty");
                if (Objects.nonNull(subject)) {
                    rejectIfTrue(errors, Objects.isNull(subject.getSubjectName()) || subject.getSubjectName().isBlank(), "subjectName", "diplomaSubjects.subjectName.empty");
                    rejectIfTrue(errors, Objects.isNull(subject.getSubjectGrade()) || subject.getSubjectGrade().isBlank(), "subjectGrade", "diplomaSubjects.subjectGrade.empty");
                }
            });
        }

        return errors;
    }
}
