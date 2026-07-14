package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalSpecialityEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface OriginalSpecialityRepository extends BaseRepository<VOriginalSpecialityEntity, String>, OriginalSpecialitySearchRepository {

}
