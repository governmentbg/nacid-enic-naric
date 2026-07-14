package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.DocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:19
 */
public interface DocTypeRepository extends NomenclatureBaseRepository<Integer, DocTypeEntity, DocTypeFilterDTO>, DocTypeSearchRepository {
}
