package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ApplicationTypeSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationTypeFilterDTO;

public interface ApplicationTypeRepository extends NomenclatureBaseRepository<String, ApplicationTypeEntity, ApplicationTypeFilterDTO>, ApplicationTypeSearchRepository {

}
