package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ProfGroupSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfGroupFilterDTO;

public interface ProfGroupRepository extends NomenclatureBaseRepository<Integer, ProfGroupEntity, ProfGroupFilterDTO>, ProfGroupSearchRepository {

}
