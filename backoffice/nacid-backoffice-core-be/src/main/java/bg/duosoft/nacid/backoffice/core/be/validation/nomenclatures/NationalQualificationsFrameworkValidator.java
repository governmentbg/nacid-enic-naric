package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.NationalQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class NationalQualificationsFrameworkValidator extends BaseIntegerKeyNomenclatureValidator<NationalQualificationFrameworkDTO, NationalQualificationFrameworkFilterDTO> {
    @Override
    protected void validateAdditional(List<ValidationError> errors, NationalQualificationFrameworkDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getCountry()) || Objects.isNull(obj.getCountry().getId()), "country.id", "validation.field.required");
    }
}
