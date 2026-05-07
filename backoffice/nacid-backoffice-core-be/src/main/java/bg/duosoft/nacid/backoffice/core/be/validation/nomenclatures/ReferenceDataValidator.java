package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReferenceDataValidator implements Validator<ReferenceDataDTO> {

    @Override
    public List<ValidationError> validate(ReferenceDataDTO obj, Object... args) {
        boolean isCreate = (Boolean) args[0];
        ReferenceDataService service = (ReferenceDataService) args[1];

        List<ValidationError> errors = new ArrayList<>();
        rejectIfEmptyString(errors, obj.getId(), "id", "validation.field.required");
        if (StringUtils.hasText(obj.getId())) {
            rejectIfTrue(errors, obj.getId().length() > MAX_INPUT_LENGTH_20, "id", "validation.charCount.invalid.20");
        }

        rejectIfEmptyString(errors, obj.getDomain(), "domain", "validation.field.required");
        if (StringUtils.hasText(obj.getDomain())) {
            rejectIfTrue(errors, obj.getDomain().length() > MAX_INPUT_LENGTH_XXS, "domain", "validation.charCount.invalid.50");
        }

        if (isCreate) { //isCreate
            if (StringUtils.hasText(obj.getId()) && StringUtils.hasText(obj.getDomain())) {
                ReferenceDataDTO referenceDataDTO = (service).selectById(obj.getDomain(), obj.getId());
                if (Objects.nonNull(referenceDataDTO)) {
                    reject(errors, "id", "validation.id.taken");
                    reject(errors, "domain", "validation.id.taken");
                }
            }
        } else { //isEdit
            if (StringUtils.hasText(obj.getId()) && StringUtils.hasText(obj.getDomain())) {
                ReferenceDataDTO referenceDataDTO = (service).selectById(obj.getDomain(), obj.getId());
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
