package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.AutocompleteViewRepository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AutocompleteViewRepositoryImpl extends BaseRepositoryCustomImpl implements AutocompleteViewRepository {

    public abstract String getColumnName();

    public abstract Class getTargetClass();

    @Override
    public List<String> selectRecords(AutocompleteViewFilterDTO filter) {
        TypedQuery<String> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    private TypedQuery<String> createQuery(AutocompleteViewFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(DISTINCT(r)) " : " DISTINCT(r." + getColumnName() + ") ");
        queryBuilder.append(" FROM ").append(getTargetClass().getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            queryBuilder.append(" AND LOWER(r." + getColumnName() + ") like LOWER(:name) ");
            queryParameters.put("name", "%" + name + "%");
        }
        queryBuilder.append(" ORDER BY ").append("r.").append(getColumnName());

        Class<? extends Serializable> queryClass = isCount ? Number.class : String.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
