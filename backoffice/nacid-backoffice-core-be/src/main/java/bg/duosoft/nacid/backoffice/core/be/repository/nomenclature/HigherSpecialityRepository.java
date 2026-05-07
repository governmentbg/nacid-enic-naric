package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.HigherSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VHigherSpecialityEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface HigherSpecialityRepository extends BaseRepository<VHigherSpecialityEntity, String>, HigherSpecialitySearchRepository {

}
