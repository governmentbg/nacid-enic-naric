package bg.duosoft.nacidservicesbe.validation.common.education;

import bg.duosoft.nacidfrontofficedto.services.common.education.*;
import bg.duosoft.nacidservicesbe.validation.utils.ValidationMessageCodes;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.09.2022
 * Time: 13:23
 */
public interface EducationsValidator<T> extends Validator<T> {

    default void validateRudiEducationDetails(List<ValidationError> errors, RudiEducationDetailsDTO educationDetails, boolean startEndRequired){
        if(educationDetails.getDiploma() != null){
            rejectIfStringLengthBigger(errors, educationDetails.getDiploma().getNumber(), 50, "diploma.number");
            rejectIfStringLengthBigger(errors, educationDetails.getDiploma().getSeries(), 15, "diploma.series");
            rejectIfStringLengthBigger(errors, educationDetails.getDiploma().getRegistrationNumber(), 15, "diploma.registrationNumber");
        }

        if(educationDetails.getUniversitiesData() == null || educationDetails.getUniversitiesData().size() == 0){
            errors.add(ValidationError.builder().pointer("universitiesData.0.name").message(ValidationMessageCodes.REQUIRED_CODE).build());
        } else {
            for(int i = 0; i < educationDetails.getUniversitiesData().size(); i++){
                UniversityDataDTO data = educationDetails.getUniversitiesData().get(i);
                rejectIfEmptyString(errors, data.getName(), "universitiesData."+i+".name", ValidationMessageCodes.REQUIRED_CODE);
                if(StringUtils.hasText(data.getName())) {
                    rejectIfNotMatchRegex(errors, data.getName(), "^(.+),(.+?),(.+?)$", "universitiesData." + i + ".name", ValidationMessageCodes.UNI_NAME_INVALID_CODE);
                }
                rejectIfStringLengthBigger(errors, data.getName(), 255, "universitiesData."+i+".name");
                rejectIfStringLengthBigger(errors, data.getFaculty(), 255, "universitiesData."+i+".faculty");
                rejectIfStringLengthBigger(errors, data.getUniversityContact(), 300, "universitiesData."+i+".universityContact");
            }
        }
        if(educationDetails.getEducationPlaces() == null || educationDetails.getEducationPlaces().size() == 0){
            errors.add(ValidationError.builder().pointer("educationPlaces.0.country.id").message(ValidationMessageCodes.REQUIRED_CODE).build());
            errors.add(ValidationError.builder().pointer("educationPlaces.0.city").message(ValidationMessageCodes.REQUIRED_CODE).build());
        } else {
            for(int i = 0; i < educationDetails.getEducationPlaces().size(); i++){
                EducationPlaceDTO data = educationDetails.getEducationPlaces().get(i);
                rejectIfEmptyString(errors, data.getCountry() != null ? data.getCountry().getId(): null, "educationPlaces."+i+".country.id", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfEmptyString(errors, data.getCity(), "educationPlaces."+i+".city", ValidationMessageCodes.REQUIRED_CODE);
                rejectIfStringLengthBigger(errors, data.getCity(), 30, "educationPlaces."+i+".city");
            }
        }

        validateEducationStartAndEnd(errors, educationDetails, startEndRequired);

        if(StringUtils.hasText(educationDetails.getEducationDuration())) {
            rejectIfNotMatchRegex(errors, educationDetails.getEducationDuration(), RegexUtils.DECIMAL_NUMBER_VALIDATION_REGEX, "educationDuration", ValidationMessageCodes.INVALID_CODE);
            rejectIfEmptyString(errors, educationDetails.getEducationDurationType() != null? educationDetails.getEducationDurationType().getId(): null, "educationDurationType.id",  ValidationMessageCodes.REQUIRED_CODE);
        }
        rejectIfNotMatchRegex(errors, educationDetails.getCredits(), RegexUtils.DECIMAL_NUMBER_VALIDATION_REGEX, "credits", ValidationMessageCodes.INVALID_CODE);
        rejectIfEmptyString(errors, educationDetails.getOriginalGainedLevel(), "originalGainedLevel", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfStringLengthBigger(errors, educationDetails.getOriginalGainedLevel(), 255, "originalGainedLevel");
        rejectIfEmptyString(errors, educationDetails.getOriginalGainedLevelTranslated(), "originalGainedLevelTranslated", ValidationMessageCodes.REQUIRED_CODE);
        rejectIfStringLengthBigger(errors, educationDetails.getOriginalGainedLevelTranslated(), 255, "originalGainedLevelTranslated");
    }

    private void validateEducationStartAndEnd(List<ValidationError> errors, RudiEducationDetailsDTO educationDetails, boolean startEndRequired) {
        if(startEndRequired) {
            rejectIfEmptyString(errors, educationDetails.getStartOfEducation(), "startOfEducation", ValidationMessageCodes.REQUIRED_CODE);
            rejectIfEmptyString(errors, educationDetails.getEndOfEducation(), "endOfEducation", ValidationMessageCodes.REQUIRED_CODE);
        }
        rejectIfNotMatchRegex(errors, educationDetails.getStartOfEducation(), RegexUtils.YEAR_VALIDATION_REGEX, "startOfEducation", ValidationMessageCodes.INVALID_CODE);
        rejectIfNotMatchRegex(errors, educationDetails.getEndOfEducation(), RegexUtils.YEAR_VALIDATION_REGEX, "endOfEducation", ValidationMessageCodes.INVALID_CODE);
        if(StringUtils.hasText(educationDetails.getStartOfEducation()) && StringUtils.hasText(educationDetails.getEndOfEducation())){
            try{
                if(Integer.parseInt(educationDetails.getStartOfEducation()) > Integer.parseInt(educationDetails.getEndOfEducation())){
                    reject(errors, "startOfEducation", ValidationMessageCodes.START_YEAR_BIG_CODE);
                }
            } catch (Exception e){}
        }
        if(educationDetails.getDiploma() != null && educationDetails.getDiploma().getDate() != null){
            Integer diplomaDateYear = educationDetails.getDiploma().getDate().getYear();
            if(StringUtils.hasText(educationDetails.getEndOfEducation())) {
                try {
                    if (Integer.parseInt(educationDetails.getEndOfEducation()) > diplomaDateYear) {
                        reject(errors, "endOfEducation", ValidationMessageCodes.END_YEAR_BIGGER_THAN_DIPLOMA_YEAR_CODE);
                    }
                } catch (Exception e) {
                }
            }
            if(StringUtils.hasText(educationDetails.getStartOfEducation())){
                try{
                    if(Integer.parseInt(educationDetails.getStartOfEducation()) > diplomaDateYear){
                        reject(errors, "startOfEducation", ValidationMessageCodes.START_YEAR_BIGGER_THAN_DIPLOMA_YEAR_CODE);
                    }
                } catch (Exception e){}
            }
        }
    }

    default void validateWithRecognitionCategory(List<ValidationError> errors, WithRecognitionCategory withRecognitionCategory){
        rejectIfEmptyString(errors, withRecognitionCategory.getRecognitionCategory() != null ? withRecognitionCategory.getRecognitionCategory().getId() : null, "recognitionCategory.id", ValidationMessageCodes.REQUIRED_CODE);
    }

    default void validateWithSpecialities(List<ValidationError> errors, WithSpecialities withSpecialities, String basePointer){
        if(!StringUtils.hasText(basePointer)){
            basePointer = "";
        } else {
            basePointer = basePointer+".";
        }
        if(withSpecialities.getSpecialitySingle() != null){
            rejectIfStringLengthBigger(errors, withSpecialities.getSpecialitySingle().getName(), 255, basePointer+"specialitySingle.name");
            rejectIfStringLengthBigger(errors, withSpecialities.getSpecialitySingle().getOriginalName(), 255, basePointer+"specialitySingle.originalName");
        }
        if((withSpecialities.getSpecialitySingle() == null || !StringUtils.hasText(withSpecialities.getSpecialitySingle().getName())) && (withSpecialities.getSpecialities() == null || withSpecialities.getSpecialities().size() == 0 )){
            errors.add(ValidationError.builder().pointer(basePointer+"specialitySingle.name").message(ValidationMessageCodes.REQUIRED_CODE).build());
        }
        if(withSpecialities.getSpecialities() != null && withSpecialities.getSpecialities().stream().anyMatch(spec -> spec == null || !StringUtils.hasText(spec.getName()))){
            errors.add(ValidationError.builder().pointer(basePointer+"specialitySingle.name").message(ValidationMessageCodes.EMPTY_VALUE_IN_ARRAY_CODE).build());
        }
        if(withSpecialities.getSpecialities() != null && withSpecialities.getSpecialities().stream().anyMatch(spec -> spec != null && (spec.getName().length() > 255 || (spec.getOriginalName() != null && spec.getOriginalName().length() > 255)))){
            errors.add(ValidationError.builder().pointer(basePointer+"specialitySingle.name").message(ValidationMessageCodes.BAD_VALUE_IN_ARRAY_CODE).build());
        }
    }

    default void validateWithGainedQualification(List<ValidationError> errors, WithGainedQualification withQualif, String basePointer){
        if(!StringUtils.hasText(basePointer)){
            basePointer = "";
        } else {
            basePointer = basePointer+".";
        }
        rejectIfStringLengthBigger(errors, withQualif.getGainedQualification(), 255, basePointer+"gainedQualification");
        rejectIfStringLengthBigger(errors, withQualif.getOriginalGainedQualification(), 255, basePointer+"originalGainedQualification");
    }

    default void validateWithPreviousUniversityDiploma(List<ValidationError> errors, WithPreviousUniversityDiploma withPrevDiploma) {
        if(withPrevDiploma.getPreviousUniversityDiploma() != null){
            rejectIfNotMatchRegex(errors, withPrevDiploma.getPreviousUniversityDiploma().getGraduationYear(), RegexUtils.YEAR_VALIDATION_REGEX, "previousUniversityDiploma.graduationYear", ValidationMessageCodes.INVALID_CODE);
            rejectIfStringLengthBigger(errors, withPrevDiploma.getPreviousUniversityDiploma().getUniversityName(), 255, "previousUniversityDiploma.universityName");
            rejectIfStringLengthBigger(errors, withPrevDiploma.getPreviousUniversityDiploma().getSpeciality(), 255, "previousUniversityDiploma.speciality");
        }
    }

    default void validateGraduationWay(List<ValidationError> errors, RudiEducationDetailsDTO educationDetails){
        if(educationDetails.getGraduationWay() == null || educationDetails.getGraduationWay().size() == 0){
            reject(errors, "graduationWay", ValidationMessageCodes.REQUIRED_CODE);
        }
    }
}
