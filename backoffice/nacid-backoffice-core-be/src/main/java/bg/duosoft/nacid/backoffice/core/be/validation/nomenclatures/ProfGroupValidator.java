package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfGroupFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfGroupValidator extends BaseIntegerKeyNomenclatureValidator<ProfGroupDTO, ProfGroupFilterDTO> {
    @Override
    protected void validateAdditional(List<ValidationError> errors, ProfGroupDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getEducationArea()) || Objects.isNull(obj.getEducationArea().getId()), "educationArea.id", "validation.field.required");
    }
}
