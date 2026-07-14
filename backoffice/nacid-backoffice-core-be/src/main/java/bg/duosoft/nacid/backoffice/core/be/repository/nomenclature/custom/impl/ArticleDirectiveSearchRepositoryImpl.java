package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ArticleDirectiveSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ArticleDirectiveEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ArticleDirectiveSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, ArticleDirectiveEntity, ArticleDirectiveFilterDTO> implements ArticleDirectiveSearchRepository {

    @Override
    protected Class<ArticleDirectiveEntity> getEntityClass() {
        return ArticleDirectiveEntity.class;
    }
}
