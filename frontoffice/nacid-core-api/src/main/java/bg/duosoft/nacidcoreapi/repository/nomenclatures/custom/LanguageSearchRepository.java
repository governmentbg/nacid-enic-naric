package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:47
 */
public interface LanguageSearchRepository extends NomenclatureSearchBaseRepository<String, LanguageEntity, LanguageFilterDTO> {
}
