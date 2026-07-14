package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CountrySearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;

public interface CountryRepository extends NomenclatureBaseRepository<String, CountryEntity, CountryFilterDTO>, CountrySearchRepository{

}
