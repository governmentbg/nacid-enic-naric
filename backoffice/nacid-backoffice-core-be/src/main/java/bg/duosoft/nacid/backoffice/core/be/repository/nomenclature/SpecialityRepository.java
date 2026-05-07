package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSpecialityEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SpecialityRepository extends BaseRepository<VSpecialityEntity, String>, SpecialitySearchRepository {

}
