package bg.duosoft.nacidservicesbe.validation.unichecks;

import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.education.EducationsValidator;
import bg.duosoft.nacidservicesbe.validation.common.person.PersonsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 17:14
 */
@Component
public class UniChecksEducationDetailsValidator implements EducationsValidator<UniChecksEducationDetailsDTO>, PersonsValidator<UniChecksEducationDetailsDTO> {

    @Override
    public List<ValidationError> validate(UniChecksEducationDetailsDTO educationDetails, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();

        validateRudiEducationDetails(errors, educationDetails, true);
        validateWithRecognitionCategory(errors, educationDetails);
        validateWithSpecialities(errors, educationDetails, null);
        validateWithGainedQualification(errors, educationDetails, null);

        if(!Boolean.TRUE.equals(educationDetails.getStatute()) && !Boolean.TRUE.equals(educationDetails.getAuthenticity()) && !Boolean.TRUE.equals(educationDetails.getRecommendation())){
            reject(errors, "statute", ValidationMessageCodes.SELECT_CODE);
        }
        validateNaturalPerson(errors, educationDetails.getDiplomaHolder(), "diplomaHolder", true, true);
        if(StringUtils.hasText(educationDetails.getDiplomaHolderEan())){
            rejectIfNotMatchRegex(errors, educationDetails.getDiplomaHolderEan(), RegexUtils.STUDENT_EAN_REGEX, "diplomaHolderEan", ValidationMessageCodes.INVALID_CODE);
        }

        rejectIfEmptyString(errors, educationDetails.getServiceType() != null? educationDetails.getServiceType().getId() : null, "serviceType.id", ValidationMessageCodes.REQUIRED_CODE);

        return errors;
    }
}
