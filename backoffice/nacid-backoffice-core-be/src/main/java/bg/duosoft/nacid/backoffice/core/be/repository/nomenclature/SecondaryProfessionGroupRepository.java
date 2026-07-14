package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SecondaryProfessionGroupSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;

public interface SecondaryProfessionGroupRepository extends NomenclatureBaseRepository<Integer, SecondaryProfessionGroupEntity, SecondaryProfessionGroupFilterDTO>, SecondaryProfessionGroupSearchRepository {
}
