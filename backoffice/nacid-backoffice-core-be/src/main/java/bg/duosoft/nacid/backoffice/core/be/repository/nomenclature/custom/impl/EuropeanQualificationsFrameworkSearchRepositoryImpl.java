package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.EuropeanQualificationsFrameworkSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EuropeanQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.EuropeanQualificationFrameworkFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class EuropeanQualificationsFrameworkSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, EuropeanQualificationsFrameworkEntity, EuropeanQualificationFrameworkFilterDTO> implements EuropeanQualificationsFrameworkSearchRepository {

    @Override
    protected Class<EuropeanQualificationsFrameworkEntity> getEntityClass() {
        return EuropeanQualificationsFrameworkEntity.class;
    }

}
