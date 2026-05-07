package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.BolognaCycleSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.BolognaCycleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.BolognaCycleFilterDTO;

public interface BolognaCycleRepository extends NomenclatureBaseRepository<Integer, BolognaCycleEntity, BolognaCycleFilterDTO>, BolognaCycleSearchRepository {

}
