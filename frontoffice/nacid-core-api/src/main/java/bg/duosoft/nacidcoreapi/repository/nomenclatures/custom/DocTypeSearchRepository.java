package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:24
 */
public interface DocTypeSearchRepository extends NomenclatureSearchBaseRepository<Integer, DocTypeEntity, DocTypeFilterDTO> {
}
