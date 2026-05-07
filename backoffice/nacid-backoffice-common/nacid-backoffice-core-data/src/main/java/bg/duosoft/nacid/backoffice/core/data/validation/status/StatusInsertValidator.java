package bg.duosoft.nacid.backoffice.core.data.validation.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.RudiRegprofStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.StatusDataBaseDTO;
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
public class StatusInsertValidator implements Validator<StatusDataBaseDTO> {

    @Override
    public List<ValidationError> validate(StatusDataBaseDTO statusData, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(statusData.getStatus()) || !StringUtils.hasText(statusData.getStatus().getId()), "status.id", "validation.field.required");

        if (statusData instanceof RudiRegprofStatusDataBaseDTO regprofStatusDataBase) {
            rejectIfTrue(errors, Objects.isNull(regprofStatusDataBase.getDocflowStatus()) || !StringUtils.hasText(regprofStatusDataBase.getDocflowStatus().getId()), "docflowStatus.id", "validation.field.required");
        }

        return errors;
    }
}
