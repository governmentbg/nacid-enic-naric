package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgEduLevelToAppTypeRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgEduLevelToAppTypeFilterDTO;
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
public class CfgEduLevelToAppTypeRepositoryCustomImpl extends BaseRepositoryCustomImpl implements CfgEduLevelToAppTypeRepositoryCustom {
    @Override
    public List<CfgEduLevelToAppTypeEntity> selectData(CfgEduLevelToAppTypeFilterDTO filter) {
        TypedQuery<CfgEduLevelToAppTypeEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int countData(CfgEduLevelToAppTypeFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(CfgEduLevelToAppTypeFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(CfgEduLevelToAppTypeEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String educationLevel = filter.getEducationLevel();
        if (StringUtils.hasText(educationLevel)) {
            queryBuilder.append(" AND r.pk.ellCode = :educationLevel ");
            queryParameters.put("educationLevel", educationLevel);
        }

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

        Class<? extends Serializable> queryClass = isCount ? Number.class : CfgEduLevelToAppTypeEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
