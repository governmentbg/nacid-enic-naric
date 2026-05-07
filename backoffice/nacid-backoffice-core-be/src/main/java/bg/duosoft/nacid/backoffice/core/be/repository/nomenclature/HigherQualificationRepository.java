package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.HigherQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VHigherQualificationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface HigherQualificationRepository extends BaseRepository<VHigherQualificationEntity, String>, HigherQualificationSearchRepository {

}
