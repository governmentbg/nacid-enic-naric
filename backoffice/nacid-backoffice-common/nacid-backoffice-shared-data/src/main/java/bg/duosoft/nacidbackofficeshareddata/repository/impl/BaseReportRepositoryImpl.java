package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseReportRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.util.ObjectUtils;

import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 31.08.2023
 * Time: 16:13
 */
@Slf4j
public abstract class BaseReportRepositoryImpl<E extends Serializable, F extends BaseFilterDTO> extends BaseRepositoryCustomImpl implements BaseReportRepository<E, F> {

    protected Class<E> entityClass;
    protected BaseReportRepositoryImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public List<E> getReportApplications(F filter) {
        WhereClauseAndParameters where = new WhereClauseAndParameters();
        prepareWhereClauseAndParameters(filter, where);
        Query q = generateQuery(filter, where, false, false);
        if (q instanceof NativeQueryImpl n) {
            log.debug("Query:" + n.getQueryString());
        }
        log.debug("Params: " + where.getParams());
        List<E> res = q.getResultList();
        log.debug("Result size:" + res.size());
        return res;
    }

    public List<Integer> getReportApplicationIds(F filter) {
        WhereClauseAndParameters where = new WhereClauseAndParameters();
        prepareWhereClauseAndParameters(filter, where);
        Query q = generateQuery(filter, where, false, true);
        List<Integer> res = q.getResultList();
        System.out.println(res.size());
        return res;
    }

    protected abstract void prepareWhereClauseAndParameters(F filter, WhereClauseAndParameters where);

    protected abstract String getOrderByColumns(F filter);
    protected String generateOrderBy(F filter) {
        String columns = getOrderByColumns(filter);
        if (ObjectUtils.isEmpty(columns)) {
            return null;
        }
        String direction = (ObjectUtils.isEmpty(filter.getOrder()) ? Sortable.ASC_ORDER : filter.getOrder());
        return Arrays.stream(columns.split(",")).map(c -> c + " " + direction).collect(Collectors.joining(", "));
    }


    public int getReportApplicationsCount(F filter) {
        WhereClauseAndParameters where = new WhereClauseAndParameters();
        prepareWhereClauseAndParameters(filter, where);
        Query q = generateQuery(filter, where, true, false);
        return ((Number)q.getSingleResult()).intValue();

    }
    @NoArgsConstructor
    protected class WhereClauseAndParameters {

        private Set<JoinTable> joinTables = new HashSet<>();
        private List<String> where = new ArrayList<>();
        private Map<String, Object> params = new HashMap<>();
        public void addJoinTable(JoinTable jt) {
            joinTables.add(jt);
        }
        public void addWhereClause(String where) {
            this.where.add(where);
        }
        public void addParam(String paramName, Object val) {
            this.params.put(paramName, val);
        }

        public String getJoinExpressions() {
            Set<JoinTable> result = new LinkedHashSet<>();
            for (JoinTable jt : joinTables) {
                getAllRelations(jt, result);
            }
            return result.size() == 0 ? "" : result.stream().map(r -> (r.getIsLeftJoin() ? "left join " : "join ") + r.getJoinExpression()).collect(Collectors.joining("\n"));
        }

        private void getAllRelations(JoinTable jt, Set<JoinTable> joinTables) {
            if (!ObjectUtils.isEmpty(jt.getRelations())) {
                jt.getRelations().forEach(jt2 -> getAllRelations(jt2, joinTables));
            }
            joinTables.add(jt);

        }
        protected String getWhereClause() {
            return where.size() == 0 ? "1 = 1" : where.stream().collect(Collectors.joining(") AND (", "(", ")"));
        }

        public Map<String, Object> getParams() {
            return params;
        }
    }

    protected <T> Query generateQuery(F filter, WhereClauseAndParameters where, boolean count, boolean onlyIds) {
        String sql = createSql(filter, where, count, onlyIds);
        System.out.println(sql);
        Query q;
        if (count || onlyIds) {
            q = em.createNativeQuery(sql);
        } else {
            q = em.createNativeQuery(sql, entityClass);
        }
        where.getParams().entrySet().stream().forEach(p -> q.setParameter(p.getKey(), p.getValue()));
        return q;
    }


    protected String createSql(F filter, WhereClauseAndParameters where, boolean count, boolean onlyIds) {
        Table t = entityClass.getAnnotation(Table.class);
        String baseTableName = (ObjectUtils.isEmpty(t.schema()) ? "" : t.schema() + ".") + t.name();
        String baseSelectStatement = count ? "count(distinct base.*)" : onlyIds ? "distinct base.id" : "distinct base.*";
        String sql = String.format("select " + baseSelectStatement + " from %s base \n", baseTableName) + where.getJoinExpressions() + "\n WHERE " + where.getWhereClause();
        if (!count) {
            String orderBy = generateOrderBy(filter);
            if (!ObjectUtils.isEmpty(orderBy)) {
                sql += "\n ORDER BY " + orderBy;
            }
            if (filter.getPageSize() != null && filter.getPageSize() > 0 && !onlyIds) {
                sql += "\n LIMIT " + filter.getPageSize();
                if (filter.getPage() != null && filter.getPage() >= 1) {
                    sql += "\n OFFSET " + (filter.getPage()) * filter.getPageSize();
                }
            }

        }
        return sql;
    }




    protected interface JoinTable {
        public String getAlias();

        public List<JoinTable> getRelations();
        public String getJoinExpression();
        boolean getIsLeftJoin();
    }
    protected enum BASE_JOIN_TABLES implements JoinTable {
        APPLICATION("apn", "common.application apn on apn.id = base.id", null),
        STATUS_HISTORY("ash", "common.app_status_history ash on ash.apn_id = apn.id", APPLICATION),
        APPLICANT("apt","common.person apt on apt.id = apn.applicant_id", APPLICATION),
        CURRENT_STATUS_HISTORY("csts","common.app_status_history csts on csts.id = (select id from common.app_status_history h1 where h1.apn_id = apn.id order by date_created desc, id desc limit 1)", APPLICATION),
        FINAL_STATUS_HISTORY("fsh", "common.app_status_history fsh on fsh.id = apn.final_status_history_id", APPLICATION),
        DOCFLOW_STATUS_HISTORY("dsh", "common.app_docflow_status_history adsh on adsh.apn_id = apn.id", APPLICATION),
        CURRENT_DOCFLOW_STATUS_HISTORY("cdsts", "common.app_docflow_status_history cdsts on cdsts.id = (select id from common.app_docflow_status_history h1 where h1.apn_id = apn.id order by date_created desc, id desc limit 1)", APPLICATION),
        ATTACHED_DOCS("aad", "common.application_attached_docs aad on aad.apn_id = apn.id", APPLICATION),
        RESPONSIBLE_USERS("rur", "common.application_responsible_users rur on rur.apn_id = apn.id", APPLICATION),
        APPLICANT_DIPLOMA_NAMES("adn", "common.applicant_diploma_names adn on adn.apn_id = apn.id", APPLICATION),
        REPRESENTATIVE("ree", "common.person ree on ree.id = apn.representative_id", APPLICATION),
        ;

        private final String alias;
        private final List<JoinTable> relations;
        private final String joinExpression;
        private final boolean isLeftJoin;

        private BASE_JOIN_TABLES(String alias, String joinExpression, JoinTable... relations) {
            this.isLeftJoin = false;
            this.alias = alias;
            this.joinExpression = joinExpression;
            this.relations = relations == null || relations.length == 0 ? null : Arrays.asList(relations);
        }
        @Override
        public String getAlias() {
            return alias;
        }

        @Override
        public List<JoinTable> getRelations() {
            return relations;
        }

        @Override
        public String getJoinExpression() {
            return joinExpression;
        }

        @Override
        public boolean getIsLeftJoin() {
            return isLeftJoin;
        }
    }

    protected enum WHERE_OPERATION_TYPE {
        EQUAL("="),
        NOT_EQUAL("!="),
        GREATER(">"),
        LESS("<"),
        GREATER_OR_EQUAL(">="),
        LESS_OR_EQUAL("<="),
        IN("IN"),
        ILIKE("ILIKE");
        private String sign;
        WHERE_OPERATION_TYPE(String sign) {
            this.sign = sign;
        }


    }

    protected void addWhereClauseAndParam(WhereClauseAndParameters where, JoinTable joinTable, WHERE_OPERATION_TYPE whereOperationType, String column, String paramName, Object value, List<String> sqlToAddWhereClauseTo) {
        where.addJoinTable(joinTable);

        where.addParam(paramName, value);
        String sql = prepareWhereClauseWithParam(joinTable, whereOperationType, column, paramName);
        if (sqlToAddWhereClauseTo == null) {
            where.addWhereClause(sql);
        } else {
            sqlToAddWhereClauseTo.add(sql);
        }
    }

    /**
     *
     * @param where
     * @param joinTable
     * @param whereOperationType
     * @param column
     * @param value
     * @param sqlToAddWhereClauseTo - kyde shte dobavi generiranata where clause. Ako e null, dobavq klauzata kym where obekta!!!
     */
    protected void addWhereClauseAndValue(WhereClauseAndParameters where, JoinTable joinTable, WHERE_OPERATION_TYPE whereOperationType, String column, Object value, List<String> sqlToAddWhereClauseTo) {
        where.addJoinTable(joinTable);
        String sql = prepareWhereClauseWithValue(joinTable, whereOperationType, column, value);
        if (sqlToAddWhereClauseTo == null) {
            where.addWhereClause(sql);
        } else {
            sqlToAddWhereClauseTo.add(sql);
        }
    }

    private String prepareWhereClauseWithParam(JoinTable joinTable, WHERE_OPERATION_TYPE whereOperationType, String column, String paramName) {
        return  String.format("%s.%s %s :%s", joinTable.getAlias(), column, whereOperationType.sign, paramName);
    }
    private String prepareWhereClauseWithValue(JoinTable joinTable, WHERE_OPERATION_TYPE whereOperationType, String column, Object value) {
        if (value == null) {
            return switch (whereOperationType) {
                case EQUAL -> String.format("%s.%s is null", joinTable.getAlias(), column);
                case NOT_EQUAL -> String.format("%s.%s is not null", joinTable.getAlias(), column);
                default -> throw new RuntimeException("Unknown operation " + whereOperationType);
            };
        }
        return  String.format("%s.%s %s %s", joinTable.getAlias(), column, whereOperationType.sign, value);
    }

    /**
     * podava se list ot selectirani ID-ta + list ot maski. Generira kriterii koito e columnName in (ids) or columnName ilike '%mask1%' or columnName ilike '%mask2%' i t.n.
     * @param where
     * @param joinTable
     * @param columnName
     * @param paramNamePrefix
     * @param ids
     * @param likes
     */
    protected void addInOrILikeCriteriaToSingleField(WhereClauseAndParameters where, JoinTable joinTable, String columnName, String paramNamePrefix, List<StringIdDTO> ids, List<String> likes) {
        List<String> criteria = new ArrayList<>();
        if (!ObjectUtils.isEmpty(ids)) {
            addWhereClauseAndParam(where, joinTable, WHERE_OPERATION_TYPE.IN, columnName, paramNamePrefix + "Ids", toStringList(ids), criteria);
        }
        if (!ObjectUtils.isEmpty(likes)) {
            AtomicInteger cnt = new AtomicInteger(1);
            likes.forEach(like -> addWhereClauseAndParam(where, joinTable, WHERE_OPERATION_TYPE.ILIKE, columnName, paramNamePrefix + "Name" + cnt.getAndIncrement(), "%" + like + "%", criteria));
        }
        if (criteria.size() > 0) {
            where.addWhereClause(criteria.stream().collect(Collectors.joining(") OR (", "(", ")")));
        }
    }

    /**
     * podava se list ot selectirani ID-ta + list ot maski. Generira kriterii koito e idColumnName in (ids) or likeColumnName ilike '%mask1%' or likeColumnName ilike '%mask2%' i t.n.
     * @param where
     * @param joinTable
     * @param idColumnName
     * @param likeColumnName
     * @param paramNamePrefix
     * @param ids
     * @param likes
     */
    protected void addInOrILikeCriteriaToDifferentFields(WhereClauseAndParameters where, JoinTable joinTable, String idColumnName, String likeColumnName, String paramNamePrefix, List<IntegerIdDTO> ids, List<String> likes) {
        List<String> criteria = new ArrayList<>();
        if (!ObjectUtils.isEmpty(ids)) {
            addWhereClauseAndParam(where, joinTable, WHERE_OPERATION_TYPE.IN, idColumnName, paramNamePrefix + "Ids", toIntegerList(ids), criteria);
        }
        if (!ObjectUtils.isEmpty(likes)) {
            AtomicInteger cnt = new AtomicInteger(1);
            likes.forEach(like -> addWhereClauseAndParam(where, joinTable, WHERE_OPERATION_TYPE.ILIKE, likeColumnName, paramNamePrefix + "Name" + cnt.getAndIncrement(), "%" + like + "%", criteria));
        }
        if (criteria.size() > 0) {
            where.addWhereClause(criteria.stream().collect(Collectors.joining(") OR (", "(", ")")));
        }
    }



    protected List<String> toStringList(List<StringIdDTO> ids) {
        return ids.stream().map(r -> r.getId()).toList();
    }
    protected List<Integer> toIntegerList(List<IntegerIdDTO> ids) {
        return ids.stream().map(r -> r.getId()).toList();
    }
}
