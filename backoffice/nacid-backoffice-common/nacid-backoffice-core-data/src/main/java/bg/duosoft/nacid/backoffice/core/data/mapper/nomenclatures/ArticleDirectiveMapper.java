package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ArticleDirectiveEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ArticleItemEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ArticleItemMapper.class})
public abstract class ArticleDirectiveMapper extends BaseNomenclatureMapper<ArticleDirectiveEntity, ArticleDirectiveDTO> {


    @AfterMapping
    protected void afterMapping(ArticleDirectiveDTO source, @MappingTarget ArticleDirectiveEntity target) {
        List<ArticleItemEntity> items = target.getItems();
        if (!CollectionUtils.isEmpty(items)) {
            for (ArticleItemEntity item : items) {
                item.setArticleDirective(target);
            }
        }
    }

}
