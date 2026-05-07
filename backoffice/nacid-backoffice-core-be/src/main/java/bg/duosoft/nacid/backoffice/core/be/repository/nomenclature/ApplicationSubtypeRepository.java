package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ApplicationSubTypeSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationSubtypeRepository extends NomenclatureBaseRepository<String, ApplicationSubtypeEntity, ApplicationSubTypeFilterDTO>, ApplicationSubTypeSearchRepository {
    @Query("SELECT ast from ApplicationSubtypeEntity ast where ast.applicationType.id = :applicationType and ((:active is null and 1 = 1) or ast.active = :active)")
    List<ApplicationSubtypeEntity> getAllByApplicationTypeIdAndActive(@Param("applicationType") String applicationType, @Param("active") Integer active);

}
