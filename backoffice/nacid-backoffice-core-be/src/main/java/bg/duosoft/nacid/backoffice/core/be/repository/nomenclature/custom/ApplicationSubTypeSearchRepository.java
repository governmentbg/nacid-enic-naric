package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;

/*
 * <ID> - ID
 * <E> - Entity
 * <F> - Filter
*/
public interface ApplicationSubTypeSearchRepository extends NomenclatureSearchBaseRepository<String, ApplicationSubtypeEntity, ApplicationSubTypeFilterDTO> {

}
