package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationCustomDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationSaveDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommissionCalendarParticipationValidator implements Validator<CommissionCalendarParticipationSaveDTO> {
    @Override
    public List<ValidationError> validate(CommissionCalendarParticipationSaveDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        if (Objects.nonNull(obj) && !CollectionUtils.isEmpty(obj.getParticipations())) {
            int chairmanCount = 0;
            for (CommissionCalendarParticipationCustomDTO participant : obj.getParticipations()) {
                if (Objects.nonNull(participant.getChairman()) && participant.getChairman()) {
                    chairmanCount = chairmanCount + 1;
                }
                if (chairmanCount > 1) {
                    rejectIfTrue(errors, true, "position", "validation.field.more.than.one.chairman");
                    break;
                }
            }
        }
        return errors;
    }
}
