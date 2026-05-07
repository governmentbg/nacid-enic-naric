package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;

/*
 * <ID> - ID
 * <E> - Entity
 * <F> - Filter
*/
public interface DocumentReceiveMethodSearchRepository extends NomenclatureSearchBaseRepository<String, DocumentReceiveMethodEntity, DocumentReceiveMethodFilterDTO> {

}
