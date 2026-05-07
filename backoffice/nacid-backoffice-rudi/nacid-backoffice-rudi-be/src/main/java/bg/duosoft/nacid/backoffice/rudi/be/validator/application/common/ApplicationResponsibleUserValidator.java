package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationResponsibleUserValidator implements Validator<RudiApplicationDTO> {
    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        List<ApplicationResponsibleUsersDTO> responsibleUsers = obj.getApplication().getResponsibleUsers();
        if (!CollectionUtils.isEmpty(responsibleUsers)) {
            ApplicationResponsibleUsersDTO applicationResponsibleUsersDTO = responsibleUsers.stream().filter(r -> !StringUtils.hasText(r.getResponsibleUser())).findFirst().orElse(null);
            rejectIfTrue(errors, Objects.nonNull(applicationResponsibleUsersDTO), "responsibleUser", "validation.field.required");
        }
        return errors;
    }
}
