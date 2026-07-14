package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EuropeanQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.EuropeanQualificationFrameworkFilterDTO;

public interface EuropeanQualificationsFrameworkSearchRepository extends NomenclatureSearchBaseRepository<Integer, EuropeanQualificationsFrameworkEntity, EuropeanQualificationFrameworkFilterDTO> {

}
