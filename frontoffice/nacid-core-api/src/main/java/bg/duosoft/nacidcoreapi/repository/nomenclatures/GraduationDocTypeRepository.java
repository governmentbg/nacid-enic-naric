package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.GraduationDocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:34
 */
public interface GraduationDocTypeRepository extends NomenclatureBaseRepository<Integer, GraduationDocTypeEntity, GraduationDocTypeFilterDTO>,GraduationDocTypeSearchRepository {

    @Query("SELECT DISTINCT g from GraduationDocTypeEntity g JOIN g.configs c WHERE c.id.educationType = ?1 ORDER BY g.name")
    List<GraduationDocTypeEntity> getAllForEducationTypeCode(String educationTypeCode);

    @Query("SELECT DISTINCT g from GraduationDocTypeEntity g JOIN g.configs c WHERE c.id.educationType = ?1 and g.active = 1 ORDER BY g.name")
    List<GraduationDocTypeEntity> getAllActiveForEducationTypeCode(String educationTypeCode);
}
