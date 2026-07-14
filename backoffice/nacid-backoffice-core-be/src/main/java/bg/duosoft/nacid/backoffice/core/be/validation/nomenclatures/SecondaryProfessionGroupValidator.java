package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecondaryProfessionGroupValidator extends BaseIntegerKeyNomenclatureValidator<SecondaryProfessionGroupDTO, SecondaryProfessionGroupFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, SecondaryProfessionGroupDTO obj, Object... args) {
//        rejectIfTrue(errors, !StringUtils.hasText(obj.getCode()), "code", "validation.field.required");
        if (StringUtils.hasText(obj.getCode())) {
            rejectIfTrue(errors, obj.getCode().length() > 10, "code", "validation.charCount.invalid.10");
        }
    }

}
