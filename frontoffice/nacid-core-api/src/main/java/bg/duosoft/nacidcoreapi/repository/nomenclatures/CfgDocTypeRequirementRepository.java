package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeRequirementEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:33
 */
public interface CfgDocTypeRequirementRepository  extends BaseRepository<CfgDocTypeRequirementEntity, Integer> {

    @Query("SELECT c FROM CfgDocTypeRequirementEntity c WHERE c.applicationTypeCode = ?1 and (c.applicationSubtypeCode is NULL or c.applicationSubtypeCode = ?2) order by c.docType.name ASC ")
    List<CfgDocTypeRequirementEntity> getByApplicationTypeAndSubtype(String applicationTypeCode, String applicationSubtypeCode);
}
