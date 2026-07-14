package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolGradeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolGradeEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface SchoolGradeRepository extends BaseRepository<VSchoolGradeEntity, String>, SchoolGradeSearchRepository {

}
