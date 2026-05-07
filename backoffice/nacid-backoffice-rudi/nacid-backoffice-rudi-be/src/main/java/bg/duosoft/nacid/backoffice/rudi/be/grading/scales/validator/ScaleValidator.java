package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator;

import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ScaleValidator implements Validator<Integer> {
    @Override
    public List<ValidationError> validate(Integer obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if (Objects.nonNull(obj)) {
            try {
                Integer scale = Integer.parseInt(obj.toString());
            } catch (Exception e) {
                rejectIfTrue(errors, true, "scale", "scale.nonNumber");
            }
        }

        return errors;
    }
}
