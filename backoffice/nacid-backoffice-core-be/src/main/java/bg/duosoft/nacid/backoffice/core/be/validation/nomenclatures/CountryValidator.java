package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryValidator extends BaseStringKeyNomenclatureValidator<CountryDTO, CountryFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, CountryDTO obj, Object... args) {
        rejectIfEmptyString(errors, obj.getOfficialName(), "officialName", "validation.field.required");
        if (StringUtils.hasText(obj.getOfficialName())) {
            rejectIfTrue(errors, obj.getOfficialName().length() > getNameLength(), "officialName", "validation.charCount.invalid.255");
        }

        rejectIfEmptyString(errors, obj.getCitizenshipName(), "citizenshipName", "validation.field.required");
        if (StringUtils.hasText(obj.getCitizenshipName())) {
            rejectIfTrue(errors, obj.getCitizenshipName().length() > getNameLength(), "citizenshipName", "validation.charCount.invalid.255");
        }
    }
}
