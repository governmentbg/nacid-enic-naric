package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfessionExperienceDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;

public interface ProfessionExperienceDocumentTypeSearchRepository extends NomenclatureSearchBaseRepository<String, ProfessionExperienceDocumentTypeEntity, ProfessionExperienceDocumentTypeFilterDTO> {

}
