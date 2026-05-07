package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalEduLevelTranslationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalEduLevelTranslatedEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface OriginalEduLevelTranslationRepository extends BaseRepository<VOriginalEduLevelTranslatedEntity, String>, OriginalEduLevelTranslationSearchRepository {

}
