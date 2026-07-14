package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.SettlementSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;

public interface SettlementRepository extends NomenclatureBaseRepository<String, EkSettlementEntity, SettlementFilterDTO>, SettlementSearchRepository {
}
