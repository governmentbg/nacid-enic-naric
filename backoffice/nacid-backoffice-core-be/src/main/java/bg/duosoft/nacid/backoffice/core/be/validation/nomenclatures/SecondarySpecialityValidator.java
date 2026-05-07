package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;


import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecondarySpecialityValidator extends BaseIntegerKeyNomenclatureValidator<SecondarySpecialityDTO, SecondarySpecialityFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, SecondarySpecialityDTO obj, Object... args) {
        rejectIfTrue(errors, StringUtils.hasText(obj.getCode()) && obj.getCode().length() > 10, "code", "validation.charCount.invalid.10");
        rejectIfTrue(errors, Objects.isNull(obj.getQualification()) || Objects.isNull(obj.getQualification().getId()), "qualification.id", "validation.field.required");
//        rejectIfTrue(errors, Objects.isNull(obj.getQualificationDegree()) || Objects.isNull(obj.getQualificationDegree().getId()), "qualificationDegree.id", "validation.field.required");
    }
}
