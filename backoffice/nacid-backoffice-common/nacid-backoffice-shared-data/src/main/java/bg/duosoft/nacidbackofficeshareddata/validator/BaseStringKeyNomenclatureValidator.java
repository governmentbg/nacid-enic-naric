package bg.duosoft.nacidbackofficeshareddata.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 11.10.2022
 * Time: 15:46
 */
public class BaseStringKeyNomenclatureValidator<D extends NomenclatureBase<String>, F extends BaseNomenclatureFilterDTO<String>> extends BaseNomenclatureValidator<String, D, F> implements Validator<D> {

    protected void validateId(List<ValidationError> errors, String id, boolean isCreate, NomenclatureServiceBase<String, D, F> service) {
        rejectIfEmptyString(errors, id, "id", "validation.field.required");
        if (StringUtils.hasText(id)) {
            rejectIfTrue(errors, id.length() > getIdLength(), "id", "validation.charCount.invalid." + getIdLength());
        }

        if (isCreate) { //isCreate
            if (StringUtils.hasText(id)) {
                D dto = service.selectById(id);
                if (Objects.nonNull(dto)) {
                    reject(errors, "id", "validation.id.taken");
                }
            }
        } else { //isEdit
            if (StringUtils.hasText(id)) {
                D dto = service.selectById(id);
                if (Objects.isNull(dto)) {
                    reject(errors, "id", "validation.field.invalid");
                }
            }
        }
    }
}
