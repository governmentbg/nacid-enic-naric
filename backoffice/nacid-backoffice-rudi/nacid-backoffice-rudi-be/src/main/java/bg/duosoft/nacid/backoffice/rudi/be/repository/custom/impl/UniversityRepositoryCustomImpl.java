package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.UniversityRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UniversityRepositoryCustomImpl extends BaseRepositoryCustomImpl implements UniversityRepositoryCustom {
    @Override
    public List<UniversityEntity> searchRecords(UniversityFilterDTO filter) {
        TypedQuery<UniversityEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int getRecordsCount(UniversityFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }


    protected <T> TypedQuery<T> createQuery(UniversityFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(UniversityEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String bgName = filter.getBgName();
        if (StringUtils.hasText(bgName)) {
            queryBuilder.append(" AND LOWER(r.bgName) like LOWER(:bgName) ");
            queryParameters.put("bgName", "%" + bgName + "%");
        }

        String orgName = filter.getOrgName();
        if (StringUtils.hasText(orgName)) {
            queryBuilder.append(" AND LOWER(r.orgName) like LOWER(:orgName) ");
            queryParameters.put("orgName", "%" + orgName + "%");
        }

        String country = filter.getCountryCode();
        if (StringUtils.hasText(country)) {
            queryBuilder.append(" AND r.country.id = :country ");
            queryParameters.put("country", country);
        }

        Boolean isActive = filter.getIsActive();
        if (Objects.nonNull(isActive)) {
            queryBuilder.append(" AND r.active = :active ");
            queryParameters.put("active", isActive ? 1 : 0);
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }

            String sortFields = NomenclatureSortFields.sorterColumnMap().get(sortColumn);
            if (StringUtils.hasText(sortFields)) {
                String[] columns = sortFields.split(",");
                String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        Class<? extends Serializable> queryClass = isCount ? Number.class : UniversityEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
