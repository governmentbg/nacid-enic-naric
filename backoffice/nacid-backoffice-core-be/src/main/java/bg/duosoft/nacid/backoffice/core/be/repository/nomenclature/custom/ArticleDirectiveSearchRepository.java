package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ArticleDirectiveEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;

public interface ArticleDirectiveSearchRepository extends NomenclatureSearchBaseRepository<Integer, ArticleDirectiveEntity, ArticleDirectiveFilterDTO> {
}
