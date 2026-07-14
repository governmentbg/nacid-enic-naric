package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondarySpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;

public interface SecondarySpecialitySearchRepository extends NomenclatureSearchBaseRepository<Integer, SecondarySpecialityEntity, SecondarySpecialityFilterDTO> {
}
