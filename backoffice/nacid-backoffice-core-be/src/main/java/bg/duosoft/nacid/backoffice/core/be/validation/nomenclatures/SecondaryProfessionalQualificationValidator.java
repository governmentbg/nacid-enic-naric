package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionalQualificationFilterDTO;
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
public class SecondaryProfessionalQualificationValidator extends BaseIntegerKeyNomenclatureValidator<SecondaryProfessionalQualificationDTO, SecondaryProfessionalQualificationFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, SecondaryProfessionalQualificationDTO obj, Object... args) {
//        rejectIfTrue(errors, Objects.isNull(obj.getProfessionGroup()) || Objects.isNull(obj.getProfessionGroup().getId()), "professionGroup.id", "validation.field.required");
        rejectIfTrue(errors, StringUtils.hasText(obj.getCode()) && obj.getCode().length() > 10, "code", "validation.charCount.invalid.10");
    }
}
