package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LanguageEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LanguageFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;

/*
 * <ID> - ID
 * <E> - Entity
 * <F> - Filter
 */

public interface LanguageSearchRepository extends NomenclatureSearchBaseRepository<String, LanguageEntity, LanguageFilterDTO> {

}
