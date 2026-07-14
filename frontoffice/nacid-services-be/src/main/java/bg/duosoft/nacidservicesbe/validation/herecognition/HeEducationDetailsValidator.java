package bg.duosoft.nacidservicesbe.validation.herecognition;

import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.education.EducationsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.07.2022
 * Time: 15:09
 */
@Component
public class HeEducationDetailsValidator implements EducationsValidator<HeEducationDetailsDTO> {

    @Override
    public List<ValidationError> validate(HeEducationDetailsDTO educationDetails, Object... args){
        List<ValidationError> errors = new ArrayList<>();

        validateRudiEducationDetails(errors, educationDetails, true);
        validateWithPreviousUniversityDiploma(errors, educationDetails);
        validateWithSpecialities(errors, educationDetails, null);
        validateWithGainedQualification(errors, educationDetails, null);

        if(educationDetails.getRecognitionAim() == null || educationDetails.getRecognitionAim().size() == 0){
            reject(errors, "recognitionAim", ValidationMessageCodes.REQUIRED_CODE);
        } else if(educationDetails.getRecognitionAim().stream().filter(aim -> aim == null || aim.getId() == null || aim.getDomain() == null).count() > 0) {
            reject(errors, "recognitionAim", ValidationMessageCodes.INVALID_CODE);
        }
        return errors;
    }
}
