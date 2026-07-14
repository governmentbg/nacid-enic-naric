package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolTypeEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SchoolTypeRepository extends BaseRepository<VSchoolTypeEntity, String>, SchoolTypeSearchRepository {

}
