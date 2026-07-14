package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.NationalUniversitySearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.NationalUniversityEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NationalUniversityRepository extends NomenclatureBaseRepository<String, NationalUniversityEntity, NationalUniversityDataFilterDTO>, NationalUniversitySearchRepository {

    @Modifying
    @Query(value = "UPDATE NationalUniversityEntity e SET e.active = 0")
    void updateAllToInactive();
}
