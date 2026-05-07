package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleItemDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleDirectiveValidator extends BaseIntegerKeyNomenclatureValidator<ArticleDirectiveDTO, ArticleDirectiveFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, ArticleDirectiveDTO obj, Object... args) {
        if (!CollectionUtils.isEmpty(obj.getItems())) {
            for (ArticleItemDTO item : obj.getItems()) {
                rejectIfEmptyString(errors, item.getName(), "item.name", "validation.field.required");
                if (StringUtils.hasText(item.getName())) {
                    rejectIfTrue(errors, item.getName().length() > 255, "item.name", "validation.charCount.invalid.255");
                }
                rejectIfEmptyBoolean(errors, item.getIsActive(), "item.isActive", "validation.field.required");

            }
        }

    }
}
