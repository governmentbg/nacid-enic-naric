package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ImiCorrespondenceSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VImiCorrespondenceEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface ImiCorrespondenceRepository extends BaseRepository<VImiCorrespondenceEntity, String>, ImiCorrespondenceSearchRepository {
}
