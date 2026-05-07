package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CivilIdTypeValidator extends BaseStringKeyNomenclatureValidator<CivilIdTypeDTO, CivilIdTypeFilterDTO> {

    @Override
    protected Integer getIdLength() {
        return 10;
    }

    @Override
    protected void validateAdditional(List<ValidationError> errors, CivilIdTypeDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getLegalType()) || Objects.isNull(obj.getLegalType().getId()), "legalType.id", "validation.field.required");
    }
}
