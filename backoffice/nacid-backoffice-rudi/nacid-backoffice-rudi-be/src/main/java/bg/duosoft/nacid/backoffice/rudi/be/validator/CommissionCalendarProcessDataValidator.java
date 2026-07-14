package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CalendarProcessDataDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.utils.StringUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommissionCalendarProcessDataValidator  implements Validator<CalendarProcessDataDTO> {
    @Override
    public List<ValidationError> validate(CalendarProcessDataDTO processDataDTO, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, StringUtils.isEmpty(processDataDTO.getStatusCode()), "statusCode", "validation.field.required");
        return errors;
    }
}
