package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationTypeFilterDTO;

/*
 * <ID> - ID
 * <E> - Entity
 * <F> - Filter
*/
public interface ApplicationTypeSearchRepository extends NomenclatureSearchBaseRepository<String, ApplicationTypeEntity, ApplicationTypeFilterDTO> {

}
