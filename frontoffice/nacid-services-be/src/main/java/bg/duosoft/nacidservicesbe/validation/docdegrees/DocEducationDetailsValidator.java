package bg.duosoft.nacidservicesbe.validation.docdegrees;

import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.validation.common.education.EducationsValidator;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
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
public class DocEducationDetailsValidator implements EducationsValidator<DocEducationDetailsDTO> {

    @Override
    public List<ValidationError> validate(DocEducationDetailsDTO educationDetails, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        validateRudiEducationDetails(errors, educationDetails, educationDetails.getRecognitionCategory() != null && "DOC".equals(educationDetails.getRecognitionCategory().getId()));
        validateWithRecognitionCategory(errors, educationDetails);
        validateGraduationWay(errors, educationDetails);
        validateWithPreviousUniversityDiploma(errors, educationDetails);
        validateDissertation(errors, educationDetails);

        return errors;
    }

    public void validateDissertation(List<ValidationError> errors, DocEducationDetailsDTO educationDetails){
        if(educationDetails.getGraduationWay() != null){
            boolean hasDissertation = educationDetails.getGraduationWay().stream().anyMatch(gw -> "DIS".equals(gw.getId()));
            if(hasDissertation){
                rejectIfEmptyString(errors, educationDetails.getDissertationTheme(), "dissertationTheme", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationThemeEn(), "dissertationThemeEn", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmpty(errors, educationDetails.getDissertationDate(), "dissertationDate", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationLanguage() != null ? educationDetails.getDissertationLanguage().getId(): null, "dissertationLanguage.id", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationBiblioTitlesCount(), "dissertationBiblioTitlesCount", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfNotMatchRegex(errors, educationDetails.getDissertationBiblioTitlesCount(), RegexUtils.NUMBER_VALIDATION_REGEX, "dissertationBiblioTitlesCount", ValidationMessageCodes.INVALID_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationPagesCount(), "dissertationPagesCount", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfNotMatchRegex(errors, educationDetails.getDissertationPagesCount(), RegexUtils.NUMBER_VALIDATION_REGEX, "dissertationPagesCount", ValidationMessageCodes.INVALID_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationAnnotation(), "dissertationAnnotation", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmptyString(errors, educationDetails.getDissertationAnnotationEn(), "dissertationAnnotationEn", ValidationMessageCodes.REQUIRED_CODE);
            }
        }
    }
}
