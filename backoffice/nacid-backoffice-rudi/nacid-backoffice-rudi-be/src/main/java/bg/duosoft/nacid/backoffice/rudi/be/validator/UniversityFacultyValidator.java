package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
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
public class UniversityFacultyValidator implements Validator<FacultyDTO> {


    @Override
    public List<ValidationError> validate(FacultyDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        UniversityDTO universityDTO = (UniversityDTO) args[0];
        rejectIfTrue(errors, Objects.isNull(universityDTO) || Objects.isNull(universityDTO.getId()), "universityId", "validation.field.required");
        rejectIfEmptyBoolean(errors, obj.getIsActive(), "isActive", "validation.field.required");
        rejectIfTrue(errors, !StringUtils.hasText(obj.getName()), "name", "validation.field.required");
        if (StringUtils.hasText(obj.getName())) {
            rejectIfTrue(errors, obj.getName().length() > 255, "name", "validation.charCount.invalid.255");
        }
        if (StringUtils.hasText(obj.getOriginalName())) {
            rejectIfTrue(errors, obj.getOriginalName().length() > 255, "originalName", "validation.charCount.invalid.255");
        }
        return errors;
    }
}
