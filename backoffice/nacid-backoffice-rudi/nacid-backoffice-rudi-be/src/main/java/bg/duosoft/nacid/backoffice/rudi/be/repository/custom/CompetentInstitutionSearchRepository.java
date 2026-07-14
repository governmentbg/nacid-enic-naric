package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CompetentInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;

public interface CompetentInstitutionSearchRepository extends NomenclatureSearchBaseRepository<Integer, CompetentInstitutionEntity, CompetentInstitutionFilterDTO> {
}
