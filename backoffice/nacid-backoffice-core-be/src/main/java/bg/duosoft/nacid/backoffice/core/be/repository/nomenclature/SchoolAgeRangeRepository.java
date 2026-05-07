package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolAgeRangeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolAgeRangeEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SchoolAgeRangeRepository extends BaseRepository<VSchoolAgeRangeEntity, String>, SchoolAgeRangeSearchRepository {

}
