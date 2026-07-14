package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ProfessionExperienceDocumentTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfessionExperienceDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;

/**
 * User: ggeorgiev
 * Date: 13.09.2022
 * Time: 13:31
 */
public interface ProfessionExperienceDocumentTypeRepository extends NomenclatureBaseRepository<String, ProfessionExperienceDocumentTypeEntity, ProfessionExperienceDocumentTypeFilterDTO>, ProfessionExperienceDocumentTypeSearchRepository {
}
