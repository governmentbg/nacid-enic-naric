package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgLegalReasonToAppTypeDTO;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LegalReasonValidator extends BaseIntegerKeyNomenclatureValidator<LegalReasonDTO, LegalReasonFilterDTO> {
    @Override
    protected void validateAdditional(List<ValidationError> errors, LegalReasonDTO obj, Object... args) {
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationStatus()) || Objects.isNull(obj.getApplicationStatus().getId()), "applicationStatus.id", "validation.field.required");
        if (StringUtils.hasText(obj.getOrdinanceArticle())) {
            rejectIfTrue(errors, obj.getOrdinanceArticle().length() > 255, "ordinanceArticle", "validation.charCount.invalid.255");
        }
        if (StringUtils.hasText(obj.getRegulationArticle())) {
            rejectIfTrue(errors, obj.getRegulationArticle().length() > 255, "regulationArticle", "validation.charCount.invalid.255");
        }
        List<CfgLegalReasonToAppTypeDTO> configs = obj.getConfigs();
        if (!CollectionUtils.isEmpty(configs)) {

            for (int i = 0; i < configs.size(); i++) {
                CfgLegalReasonToAppTypeDTO config = configs.get(i);

                ApplicationTypeDTO applicationType = config.getApplicationType();
                rejectIfTrue(errors, Objects.isNull(applicationType) || !StringUtils.hasText(applicationType.getId()), "configs." + i + ".applicationType.id", "validation.field.required");

                if (Objects.nonNull(applicationType) && StringUtils.hasText(applicationType.getId())) {
                    ApplicationSubtypeDTO applicationSubtype = config.getApplicationSubtype();
                    rejectIfTrue(errors, Objects.isNull(applicationSubtype) || !StringUtils.hasText(applicationSubtype.getId()), "configs." + i + ".applicationSubtype.id", "validation.field.required");
                }
            }

            //multiple configs by application type / subtype
            List<String> res = configs
                    .stream()
                    .filter(r -> Objects.nonNull(r.getApplicationType()) && StringUtils.hasText(r.getApplicationType().getId()) && Objects.nonNull(r.getApplicationSubtype()) && StringUtils.hasText(r.getApplicationSubtype().getId()))
                    .map(r -> Pair.of(r.getApplicationType().getId(), r.getApplicationSubtype().getId()))
                    .collect(Collectors.groupingBy(r -> r, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(v -> v.getValue() > 1)
                    .map(r -> r.getKey())
                    .map(r -> r.getFirst() + "/" + r.getSecond())
                    .toList();
            if (res.size() > 0) {
                reject(errors, "duplicatedConfigs", res.stream().collect(Collectors.joining("")));
            }
        }
    }
}
