package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;

public interface CivilIdTypeSearchRepository extends NomenclatureSearchBaseRepository<String, CivilIdTypeEntity, CivilIdTypeFilterDTO> {

}
