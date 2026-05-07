package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgEduLevelToAppTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntityPK;
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
public interface CfgEducationLevelToApplicationTypeRepository extends BaseRepository<CfgEduLevelToAppTypeEntity, CfgEduLevelToAppTypeEntityPK>, CfgEduLevelToAppTypeRepositoryCustom {
    @Query("select e from CfgEduLevelToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id = :applicationSubtype order by e.educationLevel.index")
    public List<CfgEduLevelToAppTypeEntity> getByApplicationTypeAndApplicationSubtype(@Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype);

    @Query("select distinct e.educationLevel from CfgEduLevelToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id in (:applicationSubtypes) order by e.educationLevel.index")
    List<ReferenceDataEntity> getByApplicationTypeAndApplicationSubtypes(@Param("applicationType") String applicationType, @Param("applicationSubtypes") List<String> applicationSubtypes);
}
