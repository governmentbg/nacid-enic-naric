package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DocumentReceiveMethodSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * User: ggeorgiev
 * Date: 18.07.2022
 * Time: 11:02
 */
public interface DocumentReceiveMethodRepository extends NomenclatureBaseRepository<String, DocumentReceiveMethodEntity, DocumentReceiveMethodFilterDTO>, DocumentReceiveMethodSearchRepository {
    @Modifying
    @Query("update DocumentReceiveMethodEntity r set r.defaultFlag = 0 where r.id <> :id")
    Integer resetDefaultById(@Param("id") String id);

}
