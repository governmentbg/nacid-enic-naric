package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CivilIdTypeSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 18.07.2022
 * Time: 11:02
 */
public interface CivilIdTypeRepository extends NomenclatureBaseRepository<String, CivilIdTypeEntity, CivilIdTypeFilterDTO>, CivilIdTypeSearchRepository{

    @Query("SELECT cit from CivilIdTypeEntity cit where cit.legalType.pk.id = :legalType and ((:active is null and 1 = 1) or cit.active = :active)")
    List<CivilIdTypeEntity> getAllByLegalTypeIdAndActive(@Param("legalType") String legalType, @Param("active") Integer active);
}
