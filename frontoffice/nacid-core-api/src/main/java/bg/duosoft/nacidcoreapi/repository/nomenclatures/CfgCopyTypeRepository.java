package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgCopyTypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 10.09.2025
 * Time: 15:21
 */
public interface CfgCopyTypeRepository extends BaseRepository<CfgCopyTypeEntity, Integer> {
    @Query("select cte.copyType from CfgCopyTypeEntity cte where cte.applicationTypeCode = :applicationType and (cte.applicationSubtypeCode is null or cte.applicationSubtypeCode = :applicationSubtype) and ((:onlyActive = true and cte.copyType.active = 1) or :onlyActive = false) order by cte.copyType.index, cte.copyType.name")
    public List<ReferenceDataEntity> getAllByDomainApplicationTypeSubtypeAndActiveOrderByIndexAscNameAsc(@Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype, @Param("onlyActive") boolean onlyActive);
}
