package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;
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
public class ApplicationSubTypeValidator extends BaseStringKeyNomenclatureValidator<ApplicationSubtypeDTO, ApplicationSubTypeFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, ApplicationSubtypeDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationType()) || Objects.isNull(obj.getApplicationType().getId()), "applicationType.id", "validation.field.required");
        if (Objects.nonNull(obj.getApplicationType()) && StringUtils.hasText(obj.getApplicationType().getId())) {
            rejectIfTrue(errors, obj.getApplicationType().getId().length() > 4, "applicationType.id", "validation.charCount.invalid.4");
        }
    }
}
