package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.NationalUniversitySearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.NationalUniversityEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NationalUniversitySearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, NationalUniversityEntity, NationalUniversityDataFilterDTO> implements NationalUniversitySearchRepository {

    @Override
    protected Class<NationalUniversityEntity> getEntityClass() {
        return NationalUniversityEntity.class;
    }

    @Override
    protected void additionalSearchQuery(NationalUniversityDataFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String settlementCode = filter.getSettlementCode();
        if (StringUtils.hasText(settlementCode)) {
            queryBuilder.append(" AND r.settlement.id = :settlementCode ");
            queryParameters.put("settlementCode", settlementCode );
        }

        String address = filter.getAddress();
        if (StringUtils.hasText(address)) {
            queryBuilder.append(" AND LOWER(r.address) like LOWER(:address) ");
            queryParameters.put("address", "%" + address + "%");
        }

        String zipCode = filter.getZipCode();
        if (StringUtils.hasText(zipCode)) {
            queryBuilder.append(" AND r.zipCode like LOWER(:zipCode) ");
            queryParameters.put("zipCode", "%" + zipCode + "%");
        }

        String website = filter.getWebsite();
        if (StringUtils.hasText(website)) {
            queryBuilder.append(" AND r.website like LOWER(:website) ");
            queryParameters.put("website", "%" + website + "%");
        }
    }

}
