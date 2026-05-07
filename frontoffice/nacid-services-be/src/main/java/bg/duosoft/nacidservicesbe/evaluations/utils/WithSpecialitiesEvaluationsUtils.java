package bg.duosoft.nacidservicesbe.evaluations.utils;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithSpecialities;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.01.2023
 * Time: 17:23
 */
public class WithSpecialitiesEvaluationsUtils {

    public static void evaluateSpecialities(WithSpecialities form, List<EvaluationDTO> evaluations){
        boolean specialities = false;

        if(form != null && form.getSpecialities() != null && !form.getSpecialities().isEmpty()){
            long badSpecCount = form.getSpecialities().stream().filter(spec -> spec == null || !StringUtils.hasText(spec.getName())).count();
            specialities = badSpecCount == 0;
        }

        evaluations.add(new EvaluationDTO("rule.educationDetails.specialities", specialities));
    }
}
