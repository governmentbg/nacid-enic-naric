package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfGroupFilterDTO;

public interface ProfGroupSearchRepository extends NomenclatureSearchBaseRepository<Integer, ProfGroupEntity, ProfGroupFilterDTO> {

}
