package bg.duosoft.nacid.backoffice.core.be.repository.common.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.ChangeRecordLogSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ChangeRecordLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChangeRecordLogSearchRepositoryImpl extends BaseRepositoryCustomImpl implements ChangeRecordLogSearchRepository {

    @Override
    public List<ChangeRecordLogEntity> selectByApplicationName(String applicationName, Integer page, Integer pageSize) {
        String buildQuery = selectByApplicationNameQuery();
        TypedQuery<ChangeRecordLogEntity> query = em.createQuery(buildQuery, ChangeRecordLogEntity.class);
        query.setParameter("applicationName", applicationName);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    private String selectByApplicationNameQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT r ")
                .append("FROM ChangeRecordLogEntity r ")
                .append("WHERE r.applicationName = :applicationName ")
                .append("ORDER BY r.id DESC ");
        return builder.toString();
    }

    @Override
    public List<ChangeRecordLogEntity> searchRecords(ChangeRecordLogFilterDTO filter) {
        TypedQuery<ChangeRecordLogEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(ChangeRecordLogFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(ChangeRecordLogFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(ChangeRecordLogEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String apnName = filter.getApplicationName();
        if (StringUtils.hasText(apnName)) {
            queryBuilder.append(" AND LOWER(r.applicationName) = LOWER(:apnName) ");
            queryParameters.put("apnName", apnName);
        }

        String id = filter.getId();
        if (StringUtils.hasText(id)) {
            queryBuilder.append(" AND r.recordId = :id ");
            queryParameters.put("id", id);
        }

        LocalDate dateFrom = filter.getDateFrom();
        if (Objects.nonNull(dateFrom)) {
            queryBuilder.append(" AND r.dateChanged >= :dateFrom ");
            queryParameters.put("dateFrom", dateFrom.atTime(LocalTime.MIN));
        }

        LocalDate dateTo = filter.getDateTo();
        if (Objects.nonNull(dateTo)) {
            queryBuilder.append(" AND r.dateChanged <= :dateTo ");
            queryParameters.put("dateTo", dateTo.atTime(LocalTime.MAX));
        }

        String operation = filter.getOperation();
        if (StringUtils.hasText(operation)) {
            queryBuilder.append(" AND LOWER(r.operation) like LOWER(:operation) ");
            queryParameters.put("operation", "%" + operation + "%");
        }

        String service = filter.getService();
        if (StringUtils.hasText(service)) {
            queryBuilder.append(" AND LOWER(r.service) = LOWER(:service) ");
            queryParameters.put("service", service);
        }

        String user = filter.getResponsibleUser();
        if (StringUtils.hasText(user)) {
            queryBuilder.append(" AND LOWER(r.userChanged) like LOWER(:user) ");
            queryParameters.put("user", "%" + user + "%");
        }

        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : ChangeRecordLogEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    @Override
    public List<List<Object>> selectServiceDictionary(String applicationName) {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("applicationName", applicationName);
        String queryStr = "SELECT distinct p.service,(CASE WHEN s.name is not null THEN s.name ELSE p.service END) as name, s.active FROM common.change_record_log p LEFT JOIN nomenclatures.dictionary s on s.code = p.service where p.application_name = :applicationName";
        Query query = em.createNativeQuery(queryStr);
        queryParameters.keySet().forEach(key -> query.setParameter(key, queryParameters.get(key)));
        return query.getResultList();
    }
}
