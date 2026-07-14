package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NationalQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;

public interface NationalQualificationsFrameworkSearchRepository extends NomenclatureSearchBaseRepository<Integer, NationalQualificationsFrameworkEntity, NationalQualificationFrameworkFilterDTO> {

}
