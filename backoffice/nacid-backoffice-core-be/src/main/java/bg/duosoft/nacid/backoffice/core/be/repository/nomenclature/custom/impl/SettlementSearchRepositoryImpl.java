package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SettlementSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SettlementFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SettlementSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, EkSettlementEntity, SettlementFilterDTO> implements SettlementSearchRepository {
    @Override
    protected Class<EkSettlementEntity> getEntityClass() {
        return EkSettlementEntity.class;
    }
}
