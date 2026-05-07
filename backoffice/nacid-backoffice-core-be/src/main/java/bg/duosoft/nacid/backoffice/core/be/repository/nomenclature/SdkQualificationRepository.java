package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SdkQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSdkQualificationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SdkQualificationRepository extends BaseRepository<VSdkQualificationEntity, String>, SdkQualificationSearchRepository {

}
