package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CommissionMemberPositionFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommissionMemberPositionValidator extends BaseStringKeyNomenclatureValidator<CommissionMemberPositionDTO, CommissionMemberPositionFilterDTO> {

    @Override
    protected Integer getIdLength() {
        return 4;
    }

    @Override
    protected void validateAdditional(List<ValidationError> errors, CommissionMemberPositionDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationStatus()) || Objects.isNull(obj.getApplicationStatus().getId()), "applicationStatus.id", "validation.field.required");
    }
}
