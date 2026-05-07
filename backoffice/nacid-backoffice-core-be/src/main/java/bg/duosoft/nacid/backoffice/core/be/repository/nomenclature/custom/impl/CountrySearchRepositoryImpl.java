package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CountrySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CountrySearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, CountryEntity, CountryFilterDTO> implements CountrySearchRepository {

    @Override
    protected Class<CountryEntity> getEntityClass() {
        return CountryEntity.class;
    }

    @Override
    protected void additionalSearchQuery(CountryFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String officialName = filter.getOfficialName();
        if (StringUtils.hasText(officialName)) {
            queryBuilder.append(" AND LOWER(r.officialName) like LOWER(:officialName) ");
            queryParameters.put("officialName", "%" + officialName + "%");
        }

        String citizenshipName = filter.getCitizenshipName();
        if (StringUtils.hasText(citizenshipName)) {
            queryBuilder.append(" AND LOWER(r.citizenshipName) like LOWER(:citizenshipName) ");
            queryParameters.put("citizenshipName", "%" + citizenshipName + "%");
        }
    }
}
