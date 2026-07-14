package bg.duosoft.nacid.backoffice.rudi.be.validator;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CompetentInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressFields;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressValidator;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompetentInstitutionValidator extends BaseIntegerKeyNomenclatureValidator<CompetentInstitutionDTO, CompetentInstitutionFilterDTO> {
    private final AddressValidator addressValidator;

    @Override
    protected void validateAdditional(List<ValidationError> errors, CompetentInstitutionDTO obj, Object... args) {
        List<AddressFields> requiredFields = new ArrayList<>();
        requiredFields.add(AddressFields.COUNTRY);
        requiredFields.add(AddressFields.SETTLEMENT);
        requiredFields.add(AddressFields.CITY);
        requiredFields.add(AddressFields.POST_CODE);
        requiredFields.add(AddressFields.ADDRESS);
        requiredFields.add(AddressFields.PHONE);
        errors.addAll(addressValidator.validateRequiredFields(obj.getAddress(), requiredFields));
        if (StringUtils.hasText(obj.getOriginalName())) {
            rejectIfTrue(errors, obj.getOriginalName().length() > 255, "originalName", "validation.charCount.invalid.255");
        }

        if (StringUtils.hasText(obj.getUrl())) {
            rejectIfTrue(errors, obj.getUrl().length() > 255, "url", "validation.charCount.invalid.255");
        }
    }


}
