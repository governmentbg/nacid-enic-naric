package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgServiceTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgServiceTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgServiceTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Repository
@RequiredArgsConstructor
public class CfgServiceTypeRepositoryCustomImpl extends BaseRepositoryCustomImpl implements CfgServiceTypeRepositoryCustom {
    @Override
    public List<CfgServiceTypeEntity> selectServiceTypeData(CfgServiceTypeFilterDTO filter) {
        TypedQuery<CfgServiceTypeEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int countServiceTypeData(CfgServiceTypeFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(CfgServiceTypeFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(CfgServiceTypeEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String applicationType = filter.getApplicationType();
        if (StringUtils.hasText(applicationType)) {
            queryBuilder.append(" AND r.applicationType.id = :applicationType ");
            queryParameters.put("applicationType", applicationType);
        }

        String applicationSubType = filter.getApplicationSubType();
        if (StringUtils.hasText(applicationSubType)) {
            queryBuilder.append(" AND r.applicationSubtype.id = :applicationSubType ");
            queryParameters.put("applicationSubType", applicationSubType);
        }

        String liabilityCode = filter.getLiabilityCode();
        if (StringUtils.hasText(liabilityCode)) {
            queryBuilder.append(" AND LOWER(r.liabilityCode) like LOWER(:liabilityCode) ");
            queryParameters.put("liabilityCode", "%" + liabilityCode + "%");
        }

        String serviceType = filter.getServiceType();
        if (StringUtils.hasText(serviceType)) {
            queryBuilder.append(" AND r.serviceType.pk.id = :serviceType ");
            queryParameters.put("serviceType", serviceType);
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

        Class<? extends Serializable> queryClass = isCount ? Number.class : CfgServiceTypeEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
