package bg.duosoft.nacid.backoffice.core.be.repository.common.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.ErrorLogSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ErrorLogSearchRepositoryImpl extends BaseRepositoryCustomImpl implements ErrorLogSearchRepository {

    @Override
    public List<ErrorLogEntity> searchRecords(ErrorLogFilterDTO filter) {
        TypedQuery<ErrorLogEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(ErrorLogFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(ErrorLogFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(ErrorLogEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        Boolean onlyUnresolved = filter.getOnlyUnresolved();
        if (Objects.nonNull(onlyUnresolved)) {
            if (onlyUnresolved) {
                queryBuilder.append(" AND r.resolvedDate IS NULL ");
            } else {
                queryBuilder.append(" AND r.resolvedDate IS NOT NULL ");
            }
        }

        LocalDate createdDateFrom = filter.getCreatedDateFrom();
        if (Objects.nonNull(createdDateFrom)) {
            queryBuilder.append(" AND r.createdDate >= :createdDateFrom ");
            queryParameters.put("createdDateFrom", createdDateFrom.atTime(LocalTime.MIN));
        }

        LocalDate createdDateTo = filter.getCreatedDateTo();
        if (Objects.nonNull(createdDateTo)) {
            queryBuilder.append(" AND r.createdDate <= :createdDateTo ");
            queryParameters.put("createdDateTo", createdDateTo.atTime(LocalTime.MAX));
        }

        LocalDate resolvedDateFrom = filter.getResolvedDateFrom();
        if (Objects.nonNull(resolvedDateFrom)) {
            queryBuilder.append(" AND r.resolvedDate >= :resolvedDateFrom ");
            queryParameters.put("resolvedDateFrom", resolvedDateFrom.atTime(LocalTime.MIN));
        }

        LocalDate resolvedDateTo = filter.getResolvedDateTo();
        if (Objects.nonNull(resolvedDateTo)) {
            queryBuilder.append(" AND r.resolvedDate <= :resolvedDateTo ");
            queryParameters.put("resolvedDateTo", resolvedDateTo.atTime(LocalTime.MAX));
        }

        String errorMessage = filter.getErrorMessage();
        if (StringUtils.hasText(errorMessage)) {
            queryBuilder.append(" AND LOWER(r.errorMessage) like LOWER(:errorMessage) ");
            queryParameters.put("errorMessage", "%" + errorMessage + "%");
        }

        String resolvedComment = filter.getResolvedComment();
        if (StringUtils.hasText(resolvedComment)) {
            queryBuilder.append(" AND LOWER(r.resolvedComment) like LOWER(:resolvedComment) ");
            queryParameters.put("resolvedComment", "%" + resolvedComment + "%");
        }

        String resolvedUser = filter.getResolvedUser();
        if (StringUtils.hasText(resolvedUser)) {
            queryBuilder.append(" AND LOWER(r.resolvedUser) like LOWER(:resolvedUser) ");
            queryParameters.put("resolvedUser", "%" + resolvedUser + "%");
        }

        String dataJson = filter.getDataJson();
        if (StringUtils.hasText(dataJson)) {
            queryBuilder.append(" AND LOWER(r.dataJson) like LOWER(:dataJson) ");
            queryParameters.put("dataJson", "%" + dataJson + "%");
        }

        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : ErrorLogEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }


}
