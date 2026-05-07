package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;

public interface CountrySearchRepository extends NomenclatureSearchBaseRepository<String, CountryEntity, CountryFilterDTO> {

}
