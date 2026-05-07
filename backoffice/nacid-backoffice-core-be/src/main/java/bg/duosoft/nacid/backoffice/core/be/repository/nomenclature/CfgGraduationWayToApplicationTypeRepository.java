package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgGraduationWayToAppTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 17:38
 */
public interface CfgGraduationWayToApplicationTypeRepository extends BaseRepository<CfgGraduationWayToAppTypeEntity, CfgGraduationWayToAppTypeEntityPK>, CfgGraduationWayToAppTypeRepositoryCustom {
    @Query("select e from CfgGraduationWayToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id = :applicationSubtype order by e.graduationWay.index")
    List<CfgGraduationWayToAppTypeEntity> getByApplicationTypeAndApplicationSubtype(@Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype);

    @Query("select distinct e.graduationWay from CfgGraduationWayToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id in (:applicationSubtypes) order by e.graduationWay.index")
    List<ReferenceDataEntity> getByApplicationTypeAndApplicationSubtypes(@Param("applicationType") String applicationType, @Param("applicationSubtypes") List<String> applicationSubtypes);
}
