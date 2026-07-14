package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalQualificationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface OriginalQualificationRepository extends BaseRepository<VOriginalQualificationEntity, String>, OriginalQualificationSearchRepository {

}
