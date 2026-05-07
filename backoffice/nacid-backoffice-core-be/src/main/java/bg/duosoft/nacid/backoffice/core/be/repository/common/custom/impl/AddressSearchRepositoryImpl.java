package bg.duosoft.nacid.backoffice.core.be.repository.common.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.AddressSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddressSearchRepositoryImpl extends BaseRepositoryCustomImpl implements AddressSearchRepository {

    @Override
    public List<AddressEntity> searchRecords(AddressFilterDTO filter) {
        TypedQuery<AddressEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(AddressFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected TypedQuery createQuery(AddressFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(AddressEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" LEFT JOIN r.settlement s ");
        queryBuilder.append(" WHERE 1=1 ");

        String addressType = filter.getAddressType();
        if (StringUtils.hasText(addressType)) {
            queryBuilder.append(" AND r.addressType.pk.id = :addressType ");
            queryParameters.put("addressType", addressType);
        }

        StringIdDTO country = filter.getCountry();
        if (Objects.nonNull(country)) {
            String countryId = country.getId();
            if (StringUtils.hasText(countryId)) {
                queryBuilder.append(" AND r.country.id = :countryId ");
                queryParameters.put("countryId", countryId);
            }
        }

        String contactPerson = filter.getContactPerson();
        if (StringUtils.hasText(contactPerson)) {
            queryBuilder.append(" AND LOWER(r.contactPerson) like LOWER(:contactPerson) ");
            queryParameters.put("contactPerson", "%" + contactPerson + "%");
        }


        String city = filter.getCity();
        if (StringUtils.hasText(city)) {
            queryBuilder.append(" AND ( LOWER(r.city) like LOWER(:city) OR LOWER(s.name) like LOWER(:city) ) ");
            queryParameters.put("city", "%" + city + "%");
        }

        String address = filter.getAddress();
        if (StringUtils.hasText(address)) {
            queryBuilder.append(" AND LOWER(r.address) like LOWER(:address) ");
            queryParameters.put("address", "%" + address + "%");
        }

        String postCode = filter.getPostCode();
        if (StringUtils.hasText(postCode)) {
            queryBuilder.append(" AND LOWER(r.postCode) like LOWER(:postCode) ");
            queryParameters.put("postCode", "%" + postCode + "%");
        }

        String phone = filter.getPhone();
        if (StringUtils.hasText(phone)) {
            queryBuilder.append(" AND LOWER(r.phone) like LOWER(:phone) ");
            queryParameters.put("phone", "%" + phone + "%");
        }

        String fax = filter.getFax();
        if (StringUtils.hasText(fax)) {
            queryBuilder.append(" AND LOWER(r.fax) like LOWER(:fax) ");
            queryParameters.put("fax", "%" + fax + "%");
        }

        String email = filter.getEmail();
        if (StringUtils.hasText(email)) {
            queryBuilder.append(" AND LOWER(r.email) like LOWER(:email) ");
            queryParameters.put("email", "%" + email + "%");
        }

        String postBox = filter.getPostBox();
        if (StringUtils.hasText(postBox)) {
            queryBuilder.append(" AND LOWER(r.postBox) like LOWER(:postBox) ");
            queryParameters.put("postBox", "%" + postBox + "%");
        }

        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : AddressEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

}
