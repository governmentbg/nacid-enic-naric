package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SdkSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSdkSpecialityEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SdkSpecialityRepository extends BaseRepository<VSdkSpecialityEntity, String>, SdkSpecialitySearchRepository {

}
