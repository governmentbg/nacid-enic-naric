package bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.MainDataBaseValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiReceptionBaseValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SarMainDataValidator extends MainDataBaseValidator implements Validator<RudiApplicationDTO> {
    public static final String DIPLOMA_OWNER_EAN_REGEX = "[ABCDEFGHKLMNPQRSTUVWXYZ][ABCDEFGHKLMNPQRSTUVWXYZ]\\d{5}";

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = super.validate(rudiApplicationDTO);

        TrainingCourseDTO trainingCourse = rudiApplicationDTO.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            String diplomaOwnerEan = trainingCourse.getDiplomaOwnerEan();
            if (StringUtils.hasText(diplomaOwnerEan)) {
                rejectIfNotMatchRegex(errors, diplomaOwnerEan, DIPLOMA_OWNER_EAN_REGEX, "diplomaOwnerEan", "validaiton.diplomaOwnerEan.invalid");
            }
        }

        return errors;
    }

}
