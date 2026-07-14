package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.NationalUniversityEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;

public interface NationalUniversitySearchRepository extends NomenclatureSearchBaseRepository<String, NationalUniversityEntity, NationalUniversityDataFilterDTO> {
}
