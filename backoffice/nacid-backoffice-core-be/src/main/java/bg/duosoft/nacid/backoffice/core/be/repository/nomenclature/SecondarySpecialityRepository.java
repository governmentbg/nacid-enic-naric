package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SecondarySpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondarySpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecondarySpecialityRepository extends NomenclatureBaseRepository<Integer, SecondarySpecialityEntity, SecondarySpecialityFilterDTO>, SecondarySpecialitySearchRepository {

    @Query("SELECT r from SecondarySpecialityEntity r where r.qualification.id = :profQualificationId and r.active = 1")
    List<SecondarySpecialityEntity> selectByProfQualificationIdOnlyActive(@Param("profQualificationId") Integer profQualificationId);

    @Query("SELECT r from SecondarySpecialityEntity r where r.qualification.id = :profQualificationId")
    List<SecondarySpecialityEntity> selectByProfQualificationId(@Param("profQualificationId") Integer profQualificationId);

}
