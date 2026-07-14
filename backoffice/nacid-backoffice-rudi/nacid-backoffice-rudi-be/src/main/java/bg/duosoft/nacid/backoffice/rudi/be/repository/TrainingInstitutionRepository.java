package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.TrainingInstitutionSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingInstitutionRepository extends NomenclatureBaseRepository<Integer, TrainingInstitutionEntity, TrainingInstitutionFilterDTO>, TrainingInstitutionSearchRepository {
    @Query(value = "SELECT DISTINCT i.* " +
            "FROM rudi.training_institution i " +
            "    JOIN rudi.training_institution_university tiu " +
            "         on i.id = tiu.tin_id " +
            "where tiu.uny_id in (:uniIds) ", nativeQuery = true)
    List<TrainingInstitutionEntity> selectByUniversityIds(@Param("uniIds") List<Integer> uniIds);
}
