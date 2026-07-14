package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;

public interface SecondaryProfessionGroupSearchRepository extends NomenclatureSearchBaseRepository<Integer, SecondaryProfessionGroupEntity, SecondaryProfessionGroupFilterDTO> {
}
