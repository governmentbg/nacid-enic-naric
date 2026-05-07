package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.SettlementSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;
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
