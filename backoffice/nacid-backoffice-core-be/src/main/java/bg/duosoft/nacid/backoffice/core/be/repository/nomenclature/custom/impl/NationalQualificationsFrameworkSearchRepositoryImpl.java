package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.NationalQualificationsFrameworkSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NationalQualificationsFrameworkEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NationalQualificationsFrameworkSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, NationalQualificationsFrameworkEntity, NationalQualificationFrameworkFilterDTO> implements NationalQualificationsFrameworkSearchRepository {

    @Override
    protected Class<NationalQualificationsFrameworkEntity> getEntityClass() {
        return NationalQualificationsFrameworkEntity.class;
    }

    @Override
    protected void additionalSearchQuery(NationalQualificationFrameworkFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String countryCode = filter.getCountryCode();
        if (StringUtils.hasText(countryCode)) {
            queryBuilder.append(" AND r.country.id = :countryCode ");
            queryParameters.put("countryCode", countryCode);
        }
    }
}
