package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgServiceTypeEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:20
 */
public interface CfgServiceTypeRepository extends BaseRepository<CfgServiceTypeEntity, Integer> {

    @Query("SELECT c FROM CfgServiceTypeEntity c WHERE c.applicationTypeCode = ?1 AND (c.applicationSubtypeCode IS NULL OR c.applicationSubtypeCode = ?2)")
    List<CfgServiceTypeEntity> getConfigsByApplicationTypeSubtype(String applicationTypeCode, String applicationSubtype);

    @Query("SELECT c FROM CfgServiceTypeEntity c WHERE c.applicationTypeCode = ?1 AND (c.applicationSubtypeCode IS NULL OR c.applicationSubtypeCode = ?2) AND c.serviceType.active = 1")
    List<CfgServiceTypeEntity> getOnlyActiveConfigsByApplicationTypeSubtype(String applicationTypeCode, String applicationSubtype);
}
