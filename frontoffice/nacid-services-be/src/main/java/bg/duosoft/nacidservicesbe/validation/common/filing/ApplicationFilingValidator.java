package bg.duosoft.nacidservicesbe.validation.common.filing;

import bg.duosoft.nacidfrontofficedto.services.common.application.EvaluationDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.10.2022
 * Time: 11:45
 */
public interface ApplicationFilingValidator<T> extends Validator<T> {

    default List<ValidationError> getFilingErrors(List<EvaluationDTO> evaluations){
        List<ValidationError> errors = new ArrayList<>();

        Boolean allEvalsTrue = evaluations.stream().map(e -> e.isEvaluationValue()).reduce((e1, e2) -> e1 && e2).get();
        if(allEvalsTrue == null || !allEvalsTrue){
            reject(errors, "request", "rule.application.irregular");
        }

        return errors;
    }
}
