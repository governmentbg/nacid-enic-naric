package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.LegalReasonSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LegalReasonRepository extends NomenclatureBaseRepository<Integer, LegalReasonEntity, LegalReasonFilterDTO>, LegalReasonSearchRepository {
    @Query("SELECT r from LegalReasonEntity r where r.applicationStatus.pk.id = :statusCode and (:onlyActive = false or r.active = 1)")
    List<LegalReasonEntity> selectByStatusCode(@Param("statusCode") String statusCode, @Param("onlyActive") boolean onlyActive);

    @Query("SELECT distinct r from LegalReasonEntity r join r.configs c where r.applicationStatus.pk.id = :statusCode and (:onlyActive = false or r.active = 1 or r.id = :selectedLegalReasonId) and c.applicationType.id = :applicationType and c.applicationSubtype.id = :applicationSubtype")
    List<LegalReasonEntity> selectByStatusApplicationTypeSubtype(@Param("selectedLegalReasonId")Integer selectedLegalReasonId, @Param("statusCode") String statusCode, @Param("applicationType")String applicationType, @Param("applicationSubtype") String applicationSubtype, @Param("onlyActive") boolean onlyActive);
}
