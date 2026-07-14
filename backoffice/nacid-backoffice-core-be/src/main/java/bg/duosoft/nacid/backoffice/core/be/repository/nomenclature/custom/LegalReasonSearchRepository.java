package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;

public interface LegalReasonSearchRepository extends NomenclatureSearchBaseRepository<Integer, LegalReasonEntity, LegalReasonFilterDTO> {

}
