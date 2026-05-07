package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.BolognaCycleSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.BolognaCycleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.BolognaCycleFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BolognaCycleSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, BolognaCycleEntity, BolognaCycleFilterDTO> implements BolognaCycleSearchRepository {

    @Override
    protected Class<BolognaCycleEntity> getEntityClass() {
        return BolognaCycleEntity.class;
    }

}
