package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
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
public class AppCommissionMemberDataValidator implements Validator<RudiApplicationDTO> {
    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rudiApplicationDTO.getApplicationCommissionMembers())) {
            for (ApplicationCommissionMemberDTO applicationCommissionMemberDTO : rudiApplicationDTO.getApplicationCommissionMembers()) {
                rejectIfTrue(errors, Objects.isNull(applicationCommissionMemberDTO.getProcessStatus()), "processStatus", "validation.field.required");
                rejectIfTrue(errors, Objects.isNull(applicationCommissionMemberDTO.getCommissionMember()) || Objects.isNull(applicationCommissionMemberDTO.getCommissionMember().getId()), "commissionMember.id", "validation.field.required");
                rejectIfTrue(errors, Objects.isNull(applicationCommissionMemberDTO.getCommissionMemberPosition()) || !StringUtils.hasText(applicationCommissionMemberDTO.getCommissionMemberPosition().getId()), "commissionMemberPosition.id", "validation.field.required");

            }
        }

        return errors;
    }
}
