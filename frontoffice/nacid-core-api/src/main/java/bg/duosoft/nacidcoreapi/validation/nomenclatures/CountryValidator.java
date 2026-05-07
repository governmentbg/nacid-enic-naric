package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryValidator extends BaseNomenclatureValidator<String, CountryDTO, CountryFilterDTO> {

    @Override
    protected Integer getIdLength() {
        return 2;
    }

    @Override
    protected void validateAdditional(List<ValidationError> errors, CountryDTO obj, Object... args) {
        rejectIfEmptyString(errors, obj.getOfficialName(), "officialName", "validation.field.required");
        if (StringUtils.hasText(obj.getOfficialName())) {
            rejectIfTrue(errors, obj.getOfficialName().length() > getNameLength(), "officialName", "validation.charCount.invalid.255");
        }
    }
}
