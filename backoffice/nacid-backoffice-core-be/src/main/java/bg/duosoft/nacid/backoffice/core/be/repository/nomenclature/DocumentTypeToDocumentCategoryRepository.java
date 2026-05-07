package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToDocCategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 18.08.2022
 * Time: 17:13
 */
public interface DocumentTypeToDocumentCategoryRepository extends BaseRepository<CfgDocTypeToDocCategoryEntity, Integer> {
//    @Query("select distinct a from DocumentTypeEntity a join a.details d where (:id is null or a.id = :id) and (:docCategory is null or d.id = :docCategory)  and (:applicationType is null or d.applicationType.id = :applicationType) and (d.applicationSubtype.id is null or d.applicationSubtype.id = :applicationSubtype) and (:condition is null or d.condition is null or d.condition = :condition)")
//    public List<DocumentTypeEntity> getDocumentTypes(@Param("id") Integer id, @Param("docCategory") String docCategory, @Param("applicationType") String applicationType, @Param("applicationSubtype") String applicationSubtype, @Param("condition") String condition);


    @Query(value = "SELECT c " +
            "FROM CfgDocTypeToDocCategoryEntity c " +
            "where c.documentType.id = :docType " +
            "  and c.documentCategory.pk.id = :docCategory " +
            "  and c.applicationType.id = :applicationType " +
            "  and (c.applicationSubtype is null or c.applicationSubtype.id = :applicationSubType) order by c.documentType.name")
    List<CfgDocTypeToDocCategoryEntity> selectDocumentTypeDetails(@Param("docType") Integer docType, @Param("docCategory") String docCategory,
                                                 @Param("applicationType") String applicationType,
                                                 @Param("applicationSubType") String applicationSubType);

}
