package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
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
public abstract class NomenclatureSearchRepositoryImpl<ID extends Serializable, E extends NomenclatureEntityBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends BaseRepositoryCustomImpl {

    protected abstract Class<E> getEntityClass();

    protected void additionalJoinQuery(F filter, StringBuilder queryBuilder) {

    }

    protected void additionalSearchQuery(F filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {

    }

    public List<E> searchRecords(F filter) {
        return searchRecords(filter, false);
    }

    public List<E> searchRecords(F filter, boolean hasDistinct) {
        TypedQuery<E> query = createQuery(filter, false, hasDistinct);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(F filter) {
        TypedQuery<Number> query = createQuery(filter, true, false);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    public int getRecordsCount(F filter, boolean hasDistinct) {
        TypedQuery<Number> query = createQuery(filter, true, hasDistinct);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(F filter, boolean isCount, boolean hasDistinct) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(constructSelectExpression(isCount, hasDistinct));
        queryBuilder.append(" FROM ").append(getEntityClass().getSimpleName()).append(" r");
        additionalJoinQuery(filter, queryBuilder);
        queryBuilder.append(" WHERE 1=1 ");

        additionalSearchQuery(filter, queryBuilder, queryParameters);
        commonSearchQuery(filter, queryBuilder, queryParameters);
        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : getEntityClass();
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    private String constructSelectExpression(boolean isCount, boolean hasDistinct) {
        if (isCount) {
            return hasDistinct ? " COUNT(DISTINCT r) " : " COUNT(r) ";
        } else {
           return hasDistinct ? " DISTINCT r " : " r ";
        }
    }

    private void commonSearchQuery(F filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        ID id = filter.getId();
        if (Objects.nonNull(id)) {
            if (id instanceof String idString) {
                if (StringUtils.hasText(idString)) {
                    queryBuilder.append(" AND LOWER(r.id) like LOWER(:id) ");
                    queryParameters.put("id", "%" + idString + "%");
                }
            } else if (id instanceof Number idNumber) {
                queryBuilder.append(" AND r.id = :id ");
                queryParameters.put("id", idNumber);
            }
        }
        NomenclatureSearchQueryUtils.appendNameSearchQuery(filter, queryBuilder, queryParameters);
        NomenclatureSearchQueryUtils.appendActiveSearchQuery(filter, queryBuilder, queryParameters);
    }

}
