package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReferenceDataValidator implements Validator<ReferenceDataDTO> {
    @Override
    public List<ValidationError> validate(ReferenceDataDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        rejectIfEmptyString(errors, obj.getId(), "id", "validation.field.required");
        if (StringUtils.hasText(obj.getId())) {
            rejectIfTrue(errors, obj.getId().length() > MAX_INPUT_LENGTH_20, "id", "validation.charCount.invalid.20");
        }

        rejectIfEmptyString(errors, obj.getDomain(), "domain", "validation.field.required");
        if (StringUtils.hasText(obj.getDomain())) {
            rejectIfTrue(errors, obj.getDomain().length() > MAX_INPUT_LENGTH_XXS, "domain", "validation.charCount.invalid.50");
        }

        if ((Boolean) args[0]) { //isCreate
            if (StringUtils.hasText(obj.getId()) && StringUtils.hasText(obj.getDomain())) {
                ReferenceDataDTO referenceDataDTO = ((ReferenceDataService) args[1]).selectById(obj.getDomain(), obj.getId());
                if (Objects.nonNull(referenceDataDTO)) {
                    reject(errors, "id", "validation.id.taken");
                    reject(errors, "domain", "validation.id.taken");
                }
            }
        } else { //isEdit
            if (StringUtils.hasText(obj.getId()) && StringUtils.hasText(obj.getDomain())) {
                ReferenceDataDTO referenceDataDTO = ((ReferenceDataService) args[1]).selectById(obj.getDomain(), obj.getId());
                if (Objects.isNull(referenceDataDTO)) {
                    reject(errors, "id", "validation.field.invalid");
                    reject(errors, "domain", "validation.field.invalid");
                }
            }
        }
        rejectIfEmptyString(errors, obj.getName(), "name", "validation.field.required");
        if (StringUtils.hasText(obj.getName())) {
            rejectIfTrue(errors, obj.getName().length() > MAX_INPUT_LENGTH_255, "name", "validation.charCount.invalid.255");
        }
        rejectIfEmptyBoolean(errors, obj.getIsActive(), "isActive", "validation.field.required");
        rejectIfNumberIsNull(errors, obj.getIndex(), "index", "validation.field.required");
        return errors;
    }
}
