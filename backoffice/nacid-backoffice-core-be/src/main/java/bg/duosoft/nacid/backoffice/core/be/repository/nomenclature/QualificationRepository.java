package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.QualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VQualificationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface QualificationRepository extends BaseRepository<VQualificationEntity, String>, QualificationSearchRepository {

}
