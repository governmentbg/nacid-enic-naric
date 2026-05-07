package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ArticleDirectiveSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ArticleDirectiveEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;

public interface ArticleDirectiveRepository extends NomenclatureBaseRepository<Integer, ArticleDirectiveEntity, ArticleDirectiveFilterDTO>, ArticleDirectiveSearchRepository {
}
