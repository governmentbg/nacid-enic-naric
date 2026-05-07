package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ExperienceProfessionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VExperienceProfessionEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface ExperienceProfessionRepository extends BaseRepository<VExperienceProfessionEntity, String>, ExperienceProfessionSearchRepository {

}
