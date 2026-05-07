package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommissionCalendarValidator implements Validator<CommissionCalendarDTO> {
    @Override
    public List<ValidationError> validate(CommissionCalendarDTO obj, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(obj.getSessionNum()), "sessionNum", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getSessionTime()), "sessionTime", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getStatus()) || !StringUtils.hasText(obj.getStatus().getId()), "status.id", "validation.field.required");
        return errors;
    }
}
