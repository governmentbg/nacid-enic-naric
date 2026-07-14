package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.RecognizedProfessionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VRecognizedProfessionEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface RecognizedProfessionRepository extends BaseRepository<VRecognizedProfessionEntity, String>, RecognizedProfessionSearchRepository {

}
