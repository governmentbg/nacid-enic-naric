package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 19.08.2022
 * Time: 13:31
 */
public interface DocumentTypeRepository extends NomenclatureBaseRepository<Integer, DocumentTypeEntity, DocumentTypeFilterDTO>, DocumentTypeSearchRepository {
    @Query(value = "select * from nomenclatures.doc_types where id in (SELECT distinct dte_id FROM nomenclatures.cfg_doc_type_to_doc_category where ate_code = ?1) and (?2 = false or active = 1)", nativeQuery = true)
    List<DocumentTypeEntity> findByApplicationType(String applicationType, boolean onlyActive);

    @Query(value = "SELECT DISTINCT dt.* " +
            "FROM nomenclatures.doc_types dt " +
            "         JOIN nomenclatures.cfg_doc_type_to_doc_category dc " +
            "              on dt.id = dc.dte_id " +
            "where dc.dcy_code = :docCategory " +
            "  and (case when :active is null then 1 = 1 else dt.active = :active end) " +
            "  and (case when :direction is null then 1 = 1 else dt.direction = :direction end) " +
            "  and dc.ate_code = :applicationType " +
            "  and (dc.ase_code = :applicationSubType or dc.ase_code is null) order by dt.name", nativeQuery = true)
    List<DocumentTypeEntity> selectDocumentTypes(@Param("docCategory") String docCategory, @Param("active") Integer active,
                                                 @Param("direction") String direction, @Param("applicationType") String applicationType,
                                                 @Param("applicationSubType") String applicationSubType);


}
