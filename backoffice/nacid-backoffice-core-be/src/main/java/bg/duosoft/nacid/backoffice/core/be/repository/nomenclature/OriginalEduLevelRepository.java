package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalEduLevelSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalEduLevelEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalQualificationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface OriginalEduLevelRepository extends BaseRepository<VOriginalEduLevelEntity, String>, OriginalEduLevelSearchRepository {

}
