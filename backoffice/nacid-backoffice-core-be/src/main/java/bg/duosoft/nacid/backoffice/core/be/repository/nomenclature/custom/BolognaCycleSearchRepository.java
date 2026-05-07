package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.BolognaCycleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.BolognaCycleFilterDTO;

public interface BolognaCycleSearchRepository extends NomenclatureSearchBaseRepository<Integer, BolognaCycleEntity, BolognaCycleFilterDTO> {

}
