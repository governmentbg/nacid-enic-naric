package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;

/*
 * <ID> - ID
 * <E> - Entity
 * <F> - Filter
*/
public interface CountrySearchRepository extends NomenclatureSearchBaseRepository<String, CountryEntity, CountryFilterDTO> {

}
