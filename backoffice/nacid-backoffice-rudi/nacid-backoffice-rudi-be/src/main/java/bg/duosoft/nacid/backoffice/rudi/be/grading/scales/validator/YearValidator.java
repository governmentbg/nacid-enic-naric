package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator;

import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import com.rometools.utils.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class YearValidator implements Validator<String> {

    @Override
    public List<ValidationError> validate(String obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Strings.isNull(obj), "year", "year.empty");
        try {
            int year = Integer.parseInt(obj);
            rejectIfFalse(errors, year >= 1900 && year <= LocalDate.now().getYear(), "year", "year.notInRange");
        } catch (Exception e) {
            rejectIfTrue(errors, true, "year", "year.invalid");
        }

        return errors;
    }
}
