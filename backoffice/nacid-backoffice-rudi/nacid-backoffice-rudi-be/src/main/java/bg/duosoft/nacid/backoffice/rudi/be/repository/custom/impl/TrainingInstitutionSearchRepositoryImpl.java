package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;


import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.TrainingInstitutionSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TrainingInstitutionSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, TrainingInstitutionEntity, TrainingInstitutionFilterDTO> implements TrainingInstitutionSearchRepository {

    @Override
    protected Class<TrainingInstitutionEntity> getEntityClass() {
        return TrainingInstitutionEntity.class;
    }


    @Override
    protected void additionalSearchQuery(TrainingInstitutionFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {

        String country = filter.getCountryCode();
        if (StringUtils.hasText(country)) {
            queryBuilder.append(" AND r.country.id = :country ");
            queryParameters.put("country", country);
        }

        String city = filter.getCity();
        if (StringUtils.hasText(city)) {
            queryBuilder.append(" AND (" +
                    " LOWER(r.address.city) like LOWER(:city) OR " +
                    " LOWER(r.address.settlement.settlementname) like LOWER(:city) OR " +
                    " LOWER(r.address.settlement.settlementnameen) like LOWER(:city)" +
                    ") ");
            queryParameters.put("city", "%" + city + "%");
        }

        String address = filter.getAddress();
        if (StringUtils.hasText(address)) {
            queryBuilder.append(" AND LOWER(r.address.address) like LOWER(:address) ");
            queryParameters.put("address", "%" + address + "%");
        }

        String phone = filter.getPhone();
        if (StringUtils.hasText(phone)) {
            queryBuilder.append(" AND LOWER(r.address.phone) like LOWER(:phone) ");
            queryParameters.put("phone", "%" + phone + "%");
        }

        String fax = filter.getFax();
        if (StringUtils.hasText(fax)) {
            queryBuilder.append(" AND LOWER(r.address.fax) like LOWER(:fax) ");
            queryParameters.put("fax", "%" + fax + "%");
        }

        String email = filter.getEmail();
        if (StringUtils.hasText(email)) {
            queryBuilder.append(" AND LOWER(r.address.email) like LOWER(:email) ");
            queryParameters.put("email", "%" + email + "%");
        }

        String website = filter.getWebsite();
        if (StringUtils.hasText(website)) {
            queryBuilder.append(" AND LOWER(r.webSite) like LOWER(:website) ");
            queryParameters.put("website", "%" + website + "%");
        }

        String postCode = filter.getPostCode();
        if (StringUtils.hasText(postCode)) {
            queryBuilder.append(" AND LOWER(r.address.postCode) like LOWER(:postCode) ");
            queryParameters.put("postCode", "%" + postCode + "%");
        }
    }

}
