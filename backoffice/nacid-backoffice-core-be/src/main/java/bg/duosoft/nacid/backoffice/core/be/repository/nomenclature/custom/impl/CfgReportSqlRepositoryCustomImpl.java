package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgReportSqlRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgReportSqlFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.ReportSqlSortFields;
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
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CfgReportSqlRepositoryCustomImpl extends BaseRepositoryCustomImpl implements CfgReportSqlRepositoryCustom {

    private final CfgReportFieldRepository cfgReportFieldRepository;

    @Override
    public List<CfgReportSqlEntity> selectReportSqlData(CfgReportSqlFilterDTO filter) {
        TypedQuery<CfgReportSqlEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int selectReportSqlDataCount(CfgReportSqlFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(CfgReportSqlFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(CfgReportSqlEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");


        String fieldId = filter.getFieldId();
        if (StringUtils.hasText(fieldId)) {
            queryBuilder.append(" AND (select count(f) from r.fields f where f.id like LOWER(:fieldId)) > 0 ");
            queryParameters.put("fieldId", "%" + fieldId + "%");

        }

        String id = filter.getId();
        if (StringUtils.hasText(id)) {
            queryBuilder.append(" AND LOWER(r.id) like LOWER(:id) ");
            queryParameters.put("id", "%" + id + "%");
        }

        String description = filter.getDescription();
        if (StringUtils.hasText(description)) {
            queryBuilder.append(" AND LOWER(r.description) like LOWER(:description) ");
            queryParameters.put("description", "%" + description + "%");
        }

        String sqlExpression = filter.getSqlExpression();
        if (StringUtils.hasText(sqlExpression)) {
            queryBuilder.append(" AND LOWER(r.sqlExpression) like LOWER(:sqlExpression) ");
            queryParameters.put("sqlExpression", "%" + sqlExpression + "%");
        }

        Boolean manyRowsFlag = filter.getManyRowsFlag();
        if (Objects.nonNull(manyRowsFlag)) {
            queryBuilder.append(" AND r.manyRowsFlag = :manyRowsFlag ");
            queryParameters.put("manyRowsFlag", manyRowsFlag ? 1 : 0);
        }

        Boolean groupFlag = filter.getGroupFlag();
        if (Objects.nonNull(groupFlag)) {
            queryBuilder.append(" AND r.groupFlag = :groupFlag ");
            queryParameters.put("groupFlag", groupFlag ? 1 : 0);
        }


        String startText = filter.getStartText();
        if (StringUtils.hasText(startText)) {
            queryBuilder.append(" AND LOWER(r.startText) like LOWER(:startText) ");
            queryParameters.put("startText", "%" + startText + "%");
        }

        String endText = filter.getEndText();
        if (StringUtils.hasText(endText)) {
            queryBuilder.append(" AND LOWER(r.endText) like LOWER(:endText) ");
            queryParameters.put("endText", "%" + endText + "%");
        }


        String separatorText = filter.getSeparatorText();
        if (StringUtils.hasText(separatorText)) {
            queryBuilder.append(" AND LOWER(r.separatorText) like LOWER(:separatorText) ");
            queryParameters.put("separatorText", "%" + separatorText + "%");
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }

            String sortFields = ReportSqlSortFields.sorterColumnMap().get(sortColumn);
            if (StringUtils.hasText(sortFields)) {
                String[] columns = sortFields.split(",");
                String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        Class<? extends Serializable> queryClass = isCount ? Number.class : CfgReportSqlEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
