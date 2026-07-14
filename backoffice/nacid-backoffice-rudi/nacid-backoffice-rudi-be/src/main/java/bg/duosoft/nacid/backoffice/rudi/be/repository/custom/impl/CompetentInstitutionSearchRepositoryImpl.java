package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CompetentInstitutionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CompetentInstitutionSearchRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CompetentInstitutionSearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<Integer, CompetentInstitutionEntity, CompetentInstitutionFilterDTO> implements CompetentInstitutionSearchRepository {


    @Override
    protected Class<CompetentInstitutionEntity> getEntityClass() {
        return CompetentInstitutionEntity.class;
    }


    @Override
    protected void additionalSearchQuery(CompetentInstitutionFilterDTO filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Integer id = filter.getId();
        if (Objects.nonNull(id)) {
            queryBuilder.append(" AND r.id = :id ");
            queryParameters.put("id", id);
        }

        String originalName = filter.getOriginalName();
        if (StringUtils.hasText(originalName)) {
            queryBuilder.append(" AND LOWER(r.originalName) like LOWER(:originalName) ");
            queryParameters.put("originalName", "%" + originalName + "%");
        }

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

        String postCode = filter.getPostCode();
        if (StringUtils.hasText(postCode)) {
            queryBuilder.append(" AND LOWER(r.address.postCode) like LOWER(:postCode) ");
            queryParameters.put("postCode", "%" + postCode + "%");
        }

        String url = filter.getUrl();
        if (StringUtils.hasText(url)) {
            queryBuilder.append(" AND LOWER(r.url) like LOWER(:url) ");
            queryParameters.put("url", "%" + url + "%");
        }

    }
}
