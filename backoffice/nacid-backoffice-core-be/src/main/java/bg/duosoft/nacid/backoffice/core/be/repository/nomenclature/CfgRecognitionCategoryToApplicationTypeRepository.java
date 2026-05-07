package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgRecognitionCategoryToAppTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgRecognitionCategoryToAppTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.*;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 30.05.2023
 */
public interface CfgRecognitionCategoryToApplicationTypeRepository extends BaseRepository<CfgRecognitionCategoryToAppTypeEntity, CfgRecognitionCategoryToAppTypeEntityPK>, CfgRecognitionCategoryToAppTypeRepositoryCustom {
    @Query("select e from CfgRecognitionCategoryToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id = :applicationSubtype order by e.recognitionCategory.index")
    List<CfgRecognitionCategoryToAppTypeEntity> getByApplicationTypeAndApplicationSubtype(@Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype);

    @Query("select distinct e.recognitionCategory from CfgRecognitionCategoryToAppTypeEntity e where e.applicationType.id = :applicationType and e.applicationSubtype.id in (:applicationSubtypes) order by e.recognitionCategory.index")
    List<ReferenceDataEntity> getByApplicationTypeAndApplicationSubtypes(@Param("applicationType") String applicationType, @Param("applicationSubtypes") List<String> applicationSubtypes);
}
