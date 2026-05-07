package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.NationalQualificationsFrameworkSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NationalQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NationalQualificationsFrameworkRepository extends NomenclatureBaseRepository<Integer, NationalQualificationsFrameworkEntity, NationalQualificationFrameworkFilterDTO>, NationalQualificationsFrameworkSearchRepository {
    @Query("SELECT r from NationalQualificationsFrameworkEntity r where r.country.id = :countryCode")
    List<NationalQualificationsFrameworkEntity> selectByCountryCode(@Param("countryCode") String countryCode);
}
