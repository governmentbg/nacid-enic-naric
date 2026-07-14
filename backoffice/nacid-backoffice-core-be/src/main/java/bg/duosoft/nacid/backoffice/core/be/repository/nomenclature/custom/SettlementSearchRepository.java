package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SettlementFilterDTO;

public interface SettlementSearchRepository extends NomenclatureSearchBaseRepository<String, EkSettlementEntity, SettlementFilterDTO> {
}
