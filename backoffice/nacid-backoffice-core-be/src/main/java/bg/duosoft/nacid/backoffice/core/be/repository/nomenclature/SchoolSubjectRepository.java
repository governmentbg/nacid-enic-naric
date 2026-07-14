package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolSubjectSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolSubjectEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SchoolSubjectRepository extends BaseRepository<VSchoolSubjectEntity, String>, SchoolSubjectSearchRepository {

}
