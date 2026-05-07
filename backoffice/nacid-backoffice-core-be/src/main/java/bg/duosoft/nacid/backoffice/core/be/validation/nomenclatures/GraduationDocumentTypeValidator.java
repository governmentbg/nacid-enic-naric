package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationDocumentTypeConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GraduationDocumentTypeValidator extends BaseIntegerKeyNomenclatureValidator<GraduationDocumentTypeDTO, GraduationDocumentTypeFilterDTO> {
    @Override
    protected void validateAdditional(List<ValidationError> errors, GraduationDocumentTypeDTO obj, Object... args) {
        rejectIfTrue(errors, hasExistingElements(obj.getConfigs()), "configs", "validation.duplicate.configs");
    }


    private boolean hasExistingElements(List<CfgGraduationDocumentTypeConfigDTO> configs) {
        if (!CollectionUtils.isEmpty(configs)) {
            for (int i = 0; i < configs.size(); i++) {
                CfgGraduationDocumentTypeConfigDTO config = configs.get(i);
                List<CfgGraduationDocumentTypeConfigDTO> duplicateElements = configs.stream().filter(r -> r.getCountry().getId().equals(config.getCountry().getId()) && r.getEducationType().getId().equals(config.getEducationType().getId())).collect(Collectors.toList());
                if (duplicateElements.size() > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
