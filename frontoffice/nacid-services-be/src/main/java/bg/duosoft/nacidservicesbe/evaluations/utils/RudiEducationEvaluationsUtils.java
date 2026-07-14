package bg.duosoft.nacidservicesbe.evaluations.utils;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithRecognitionCategory;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 15:52
 */
public class RudiEducationEvaluationsUtils {

    public static void evaluateUniversities(RudiEducationDetailsDTO form, List<EvaluationDTO> evaluations){
        boolean universities = false;
        if(form != null && form.getUniversitiesData() != null && !form.getUniversitiesData().isEmpty()){
            long badUniCount = form.getUniversitiesData().stream().filter(uni -> !StringUtils.hasText(uni.getName())).count();
            universities = badUniCount == 0;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.universitiesData.name", universities));
    }

    public static void evaluateEducationPlaces(RudiEducationDetailsDTO form, List<EvaluationDTO> evaluations){
        boolean eduPlaces = false;
        if(form != null && form.getEducationPlaces() != null && !form.getEducationPlaces().isEmpty()){
            long badEduPlaces = form.getEducationPlaces().stream().filter(place -> !StringUtils.hasText(place.getCity()) || place.getCountry() == null || !StringUtils.hasText(place.getCountry().getId())).count();
            eduPlaces = badEduPlaces == 0;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.educationPlaces.country.city", eduPlaces));
    }

    public static void evaluateOriginalGainedLevel(RudiEducationDetailsDTO form, List<EvaluationDTO> evaluations){
        boolean originalGainedLevel = false;
        boolean originalGainedLevelTranslated = false;

        if(form != null ){
            originalGainedLevel = StringUtils.hasText(form.getOriginalGainedLevel());
            originalGainedLevelTranslated = StringUtils.hasText(form.getOriginalGainedLevelTranslated());
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.originalGainedLevel", originalGainedLevel));
        evaluations.add(new EvaluationDTO("rule.educationDetails.originalGainedLevelTranslated", originalGainedLevelTranslated));
    }

    public static void evaluateEducationStartEnd(RudiEducationDetailsDTO form, List<EvaluationDTO> evaluations){
        boolean startOfEducation = false;
        boolean endOfEducation = false;

        if(form != null ){
            startOfEducation = form.getStartOfEducation() !=null;
            endOfEducation = form.getEndOfEducation() != null;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.startOfEducation", startOfEducation));
        evaluations.add(new EvaluationDTO("rule.educationDetails.endOfEducation", endOfEducation));
    }

    public static void evaluateRecognitionCategory(WithRecognitionCategory form, List<EvaluationDTO> evaluations){
        boolean recognitionCategory = false;
        if(form != null ) {
            recognitionCategory = form.getRecognitionCategory() != null && StringUtils.hasText(form.getRecognitionCategory().getId());
        }
        evaluations.add(new EvaluationDTO("rule.educationDetails.recognitionCategory", recognitionCategory));
    }
}
