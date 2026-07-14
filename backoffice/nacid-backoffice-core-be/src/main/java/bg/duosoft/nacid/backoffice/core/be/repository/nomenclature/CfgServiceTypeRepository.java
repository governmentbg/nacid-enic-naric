package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgServiceTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgServiceTypeEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CfgServiceTypeRepository extends BaseRepository<CfgServiceTypeEntity, Integer>, CfgServiceTypeRepositoryCustom {

    @Query("SELECT s FROM CfgServiceTypeEntity s WHERE (s.applicationType.id = :applicationType) and (s.applicationSubtype is null or s.applicationSubtype.id = :applicationSubType) ORDER BY s.serviceType.index")
    List<CfgServiceTypeEntity> getByApplicationTypeAndSubtype(String applicationType, String applicationSubType);
}
