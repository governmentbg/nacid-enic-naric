package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.CountrySearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;

public interface CountryRepository extends NomenclatureBaseRepository<String, CountryEntity, CountryFilterDTO>, CountrySearchRepository {

}
