package bg.duosoft.nacidbackofficeshareddata.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public abstract class BaseNomenclatureValidator<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> implements Validator<D> {

    protected Integer getIdLength() {
        return 4;
    }

    protected Integer getNameLength() {
        return 255;
    }

    protected void validateAdditional(List<ValidationError> errors, D obj, Object... args) {
    }

    @Override
    public List<ValidationError> validate(D obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        validateId(errors, obj.getId(), (Boolean) args[0], (NomenclatureServiceBase<ID, D, F>) args[1]);
        validateName(errors, obj.getName());
        validateIsActive(errors, obj.getIsActive());
        validateAdditional(errors, obj);

        return errors;
    }

    protected abstract void validateId(List<ValidationError> errors, ID id, boolean isCreate, NomenclatureServiceBase<ID, D, F> service);

    protected void validateName(List<ValidationError> errors, String name) {
        rejectIfEmptyString(errors, name, "name", "validation.field.required");
        if (StringUtils.hasText(name)) {
            rejectIfTrue(errors, name.length() > getNameLength(), "name", "validation.charCount.invalid." + getNameLength());
        }
    }

    protected void validateIsActive(List<ValidationError> errors, Boolean isActive) {
        rejectIfEmptyBoolean(errors, isActive, "isActive", "validation.field.required");
    }
}
