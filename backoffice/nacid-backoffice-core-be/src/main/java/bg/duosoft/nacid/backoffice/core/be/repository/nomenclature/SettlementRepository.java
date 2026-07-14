package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SettlementSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SettlementFilterDTO;
import org.springframework.data.jpa.repository.Query;

public interface SettlementRepository extends NomenclatureBaseRepository<String, EkSettlementEntity, SettlementFilterDTO>, SettlementSearchRepository {
    @Query("SELECT r from EkSettlementEntity r where r.id = :code")
    EkSettlementEntity findByCode(String code);
}
