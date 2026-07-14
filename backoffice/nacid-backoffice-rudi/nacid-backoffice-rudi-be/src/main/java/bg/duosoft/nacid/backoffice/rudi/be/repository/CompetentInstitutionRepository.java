package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CompetentInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CompetentInstitutionSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetentInstitutionRepository extends NomenclatureBaseRepository<Integer, CompetentInstitutionEntity, CompetentInstitutionFilterDTO>, CompetentInstitutionSearchRepository {
    @Query("SELECT DISTINCT u FROM CompetentInstitutionEntity u WHERE u.country.id = :id")
    List<CompetentInstitutionEntity> selectByCountry(@Param("id") String id);

    @Query("SELECT DISTINCT u FROM CompetentInstitutionEntity u WHERE u.country.id in (:ids)")
    List<CompetentInstitutionEntity> selectByCountries(@Param("ids") List<String> ids);
}
