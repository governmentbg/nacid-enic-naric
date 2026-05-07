package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.CountrySearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;

import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;
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
    }
}
