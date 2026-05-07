package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfessionExperienceDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfessionExperienceDocumentTypeValidator extends BaseStringKeyNomenclatureValidator<ProfessionExperienceDocumentTypeDTO, ProfessionExperienceDocumentTypeFilterDTO> {

    @Override
    protected Integer getNameLength() {
        return 100;
    }

    @Override
    public void validateAdditional(List<ValidationError> errors, ProfessionExperienceDocumentTypeDTO obj, Object... args) {
        rejectIfEmptyBoolean(errors, obj.getIsForExperienceCalculation(), "isForExperienceCalculation", "validation.field.required");
    }

}
