package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;

public interface TrainingInstitutionSearchRepository extends NomenclatureSearchBaseRepository<Integer, TrainingInstitutionEntity, TrainingInstitutionFilterDTO> {
}
