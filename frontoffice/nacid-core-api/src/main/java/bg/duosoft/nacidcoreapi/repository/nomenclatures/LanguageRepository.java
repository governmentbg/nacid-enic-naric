package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.LanguageSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:51
 */
public interface LanguageRepository extends NomenclatureBaseRepository<String, LanguageEntity, LanguageFilterDTO>, LanguageSearchRepository {
}
