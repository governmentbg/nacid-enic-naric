package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:15
 */
public interface CfgDocTypeRepository extends BaseRepository<CfgDocTypeEntity, Integer> {

    @Query("SELECT c FROM CfgDocTypeEntity c WHERE c.applicationTypeCode = ?1 and (c.applicationSubtypeCode IS NULL or c.applicationSubtypeCode =?2 ) order by c.docType.name")
    List<CfgDocTypeEntity> getCfgDocTypesForAppTypeAndSubtype(String applicationTypeCode, String applicationSubtypeCode);

    @Query("SELECT c FROM CfgDocTypeEntity c WHERE c.applicationTypeCode = ?1 order by c.docType.name")
    List<CfgDocTypeEntity> getCfgDocTypesForAppType(String applicationTypeCode);
}
