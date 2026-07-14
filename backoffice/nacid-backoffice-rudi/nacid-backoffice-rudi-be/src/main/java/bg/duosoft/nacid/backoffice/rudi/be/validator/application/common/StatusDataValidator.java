package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.base.ArchiveNumberValidator;
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
public class StatusDataValidator implements Validator<RudiApplicationDTO>, ArchiveNumberValidator {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO statusData, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        ApplicationDTO application = statusData.getApplication();
        if (Objects.nonNull(application)) {
            validateArchiveNumber(errors, application.getArchiveNumber(), "archiveNumber");
        }

        ApplicationRecognizedDetailsDTO applicationRecognizedDetails = statusData.getApplicationRecognizedDetails();
        if (Objects.nonNull(applicationRecognizedDetails)) {
            String recognizedQualification = applicationRecognizedDetails.getRecognizedQualification();
            if (StringUtils.hasText(recognizedQualification)) {
                rejectIfTrue(errors, recognizedQualification.length() > MAX_INPUT_LENGTH_255, "recognizedQualification", "validation.charCount.invalid.255");
            }
        }

        List<ApplicationRecognizedSpecialityDTO> recognizedSpecialities = statusData.getRecognizedSpecialities();
        if (!CollectionUtils.isEmpty(recognizedSpecialities)) {
            for (int i = 0; i < recognizedSpecialities.size(); i++) {
                ApplicationRecognizedSpecialityDTO speciality = recognizedSpecialities.get(i);
                String specialityString = speciality.getSpeciality();
                rejectIfTrue(errors, specialityString.length() > MAX_INPUT_LENGTH_255, "recognizedSpecialities-" + i, "validation.charCount.invalid.255");
            }
        }

        return errors;
    }
}
