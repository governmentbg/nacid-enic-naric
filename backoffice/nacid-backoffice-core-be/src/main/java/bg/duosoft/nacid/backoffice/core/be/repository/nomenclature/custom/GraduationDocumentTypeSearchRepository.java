package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GraduationDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;

public interface GraduationDocumentTypeSearchRepository extends NomenclatureSearchBaseRepository<Integer, GraduationDocumentTypeEntity, GraduationDocumentTypeFilterDTO> {

}
