package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAppStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:37
 */
public interface CfgAppStatusRepository extends BaseRepository<CfgAppStatusEntity, Integer> {
    @Query("SELECT s from CfgAppStatusEntity s where (s.applicationType.id = :applicationType) and (s.applicationSubtype.id is null or s.applicationSubtype.id = :applicationSubtype) and (:onlyActive = false or s.active = 1 or :currentStatus is null or s.status.pk.id = :currentStatus)")
    public List<CfgAppStatusEntity> getByApplicationTypeSubtype(@Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype, @Param("onlyActive") boolean onlyActive, @Param("currentStatus") String currentStatus);

    @Query("SELECT s from CfgAppStatusEntity s where (s.applicationType.id = :applicationType) and (:onlyActive = false or s.active = 1)")
    public List<CfgAppStatusEntity> getByApplicationType(String applicationType, boolean onlyActive);


    @Query("SELECT s from CfgAppStatusEntity s where s.commissionFlag = 1 and (:onlyActive = false or s.active = 1)")
    List<CfgAppStatusEntity> getByCommissionFlagTrue(boolean onlyActive);

    @Query("SELECT s from CfgAppStatusEntity s where s.legalFlag = 1 and s.applicationType.id = :applicationType and (:applicationSubType is null or s.applicationSubtype.id = :applicationSubType) and (:onlyActive = false or s.active = 1)")
    List<CfgAppStatusEntity> getByLegalFlagTrueAndApplicationType(String applicationType, String applicationSubType, boolean onlyActive);

}
