package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ExternalNomenclaturesMapEntity;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ExternalNomenclaturesMapRepository extends BaseRepository<ExternalNomenclaturesMapEntity, Integer> {
    List<ExternalNomenclaturesMapEntity> getAllBySystemAndNomenclatureType(String system, String nomenclatureType);
    List<ExternalNomenclaturesMapEntity> getAllBySystemAndNomenclatureTypeAndInternalNomId(String system, String nomenclatureType, String internalNomId);
    @Modifying
    void deleteAllBySystem(String system);

}
