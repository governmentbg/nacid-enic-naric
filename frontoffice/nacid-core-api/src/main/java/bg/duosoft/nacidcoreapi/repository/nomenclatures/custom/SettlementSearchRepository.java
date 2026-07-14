package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;

public interface SettlementSearchRepository extends NomenclatureSearchBaseRepository<String, EkSettlementEntity, SettlementFilterDTO> {
}
