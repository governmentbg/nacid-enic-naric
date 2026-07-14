package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.impl;

import bg.duosoft.nacidcoreapi.repository.BaseRepositoryCustomImpl;
import bg.duosoft.nacidcoreapi.util.nomenclatures.NomenclatureSearchQueryUtils;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
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

    protected void additionalSearchQuery(F filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {

    }

    public List<E> searchRecords(F filter) {
        TypedQuery<E> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(F filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(F filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(getEntityClass().getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        additionalSearchQuery(filter, queryBuilder, queryParameters);
        commonSearchQuery(filter, queryBuilder, queryParameters);
        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : getEntityClass();
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
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