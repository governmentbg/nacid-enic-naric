package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
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
public class TrainingInstitutionValidator extends BaseIntegerKeyNomenclatureValidator<TrainingInstitutionDTO, TrainingInstitutionFilterDTO> {
    private final AddressValidator addressValidator;

    @Override
    protected void validateAdditional(List<ValidationError> errors, TrainingInstitutionDTO obj, Object... args) {
        List<AddressFields> requiredFields = new ArrayList<>();
        requiredFields.add(AddressFields.COUNTRY);
        requiredFields.add(AddressFields.SETTLEMENT);
        requiredFields.add(AddressFields.CITY);
        requiredFields.add(AddressFields.ADDRESS);
        requiredFields.add(AddressFields.PHONE);
        requiredFields.add(AddressFields.POST_CODE);
        errors.addAll(addressValidator.validateRequiredFields(obj.getAddress(), requiredFields));

        if (StringUtils.hasText(obj.getWebSite())) {
            rejectIfTrue(errors, obj.getWebSite().length() > 255, "webSite", "validation.charCount.invalid.255");
        }
    }

}
