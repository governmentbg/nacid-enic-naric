package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class NationalUniversityValidator extends BaseNomenclatureValidator<String, NationalUniversityDTO, NationalUniversityDataFilterDTO> {

    @Override
    protected Integer getIdLength() {
        return 20;
    }

    @Override
    protected void validateAdditional(List<ValidationError> errors, NationalUniversityDTO obj, Object... args) {
        rejectIfEmptyString(errors, obj.getNameEn(), "nameEn", "validation.field.required");
        if (StringUtils.hasText(obj.getNameEn())) {
            rejectIfTrue(errors, obj.getNameEn().length() > getNameLength(), "nameEn", "validation.charCount.invalid.255");
        }

        rejectIfTrue(errors, Objects.isNull(obj.getSettlement()) || !StringUtils.hasText(obj.getSettlement().getId()), "settlement.id", "validation.field.required");

        rejectIfEmptyString(errors, obj.getAddress(), "address", "validation.field.required");
        if (StringUtils.hasText(obj.getAddress())) {
            rejectIfTrue(errors, obj.getAddress().length() > getNameLength(), "address", "validation.charCount.invalid.255");
        }

        rejectIfEmptyString(errors, obj.getAddressEn(), "addressEn", "validation.field.required");
        if (StringUtils.hasText(obj.getAddressEn())) {
            rejectIfTrue(errors, obj.getAddressEn().length() > getNameLength(), "addressEn", "validation.charCount.invalid.255");
        }

        rejectIfEmptyString(errors, obj.getZipCode(), "zipCode", "validation.field.required");
        if (StringUtils.hasText(obj.getZipCode())) {
            rejectIfTrue(errors, obj.getZipCode().length() > 10, "zipCode", "validation.charCount.invalid.10");
        }

        rejectIfEmptyString(errors, obj.getWebsite(), "website", "validation.field.required");
        if (StringUtils.hasText(obj.getWebsite())) {
            rejectIfTrue(errors, obj.getWebsite().length() > getNameLength(), "website", "validation.charCount.invalid.255");
        }
    }
}
