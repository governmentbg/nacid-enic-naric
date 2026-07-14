package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 14:53
 */
public class ApplicationCorrespondenceRepositoryCustomImpl implements ApplicationCorrespondenceRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<ApplicationCorrespondenceEntity> filterApplicationCorrespondence(ApplicationCorrespondenceFilter filter) {
        Map<String, Object> paramsMap = new HashMap<>();
        TypedQuery<ApplicationCorrespondenceEntity> query = em.createQuery(getQueryAndSetParams(filter, false, paramsMap), ApplicationCorrespondenceEntity.class);
        setQueryParams(paramsMap, query);

        query.setMaxResults(filter.getPageSize());
        query.setFirstResult(filter.getPage()*filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countFilteredApplicationCorrespondence(ApplicationCorrespondenceFilter filter) {
        Map<String, Object> paramsMap = new HashMap<>();
        TypedQuery<Long> query = em.createQuery(getQueryAndSetParams(filter, true, paramsMap), Long.class);
        setQueryParams(paramsMap, query);
        return query.getSingleResult().intValue();
    }

    private String getQueryAndSetParams(ApplicationCorrespondenceFilter filter, boolean isCount, Map<String, Object> paramsMap){
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount? " count(a) ": " a ")
                .append(" FROM ").append(ApplicationCorrespondenceEntity.class.getSimpleName()).append(" a ")
                .append(" WHERE 1 = 1 ");

        addUserSearch(queryBuilder, filter, paramsMap);
        addDateCreatedSearch(queryBuilder, filter, paramsMap);
        addTempNumberSearch(queryBuilder, filter, paramsMap);
        addRegistrationNumberDateSearch(queryBuilder, filter, paramsMap)  ;
        addReadStatusDateSearch(queryBuilder, filter, paramsMap);

        if(!isCount && filter.getOrder() != null && filter.getOrderBy() != null){
            queryBuilder.append(" ORDER BY ").append(getDBOrderBy(filter.getOrderBy(), (filter.getOrder().equals("asc")? " asc ": " desc ")));
        }

        return queryBuilder.toString();
    }

    private void addUserSearch(StringBuilder queryBuilder, ApplicationCorrespondenceFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getUser())){
            queryBuilder.append(" and a.application.userCreated = :user ");
            paramsMap.put("user", filter.getUser());
        }
    }

    private void addDateCreatedSearch(StringBuilder queryBuilder, ApplicationCorrespondenceFilter filter, Map<String, Object> paramsMap){
        if(filter.getDateCreatedFrom() != null){
            queryBuilder.append(" and a.dateCreated >= :dateFrom ");
            paramsMap.put("dateFrom", filter.getDateCreatedFrom().atStartOfDay());
        }

        if(filter.getDateCreatedTo() != null){
            queryBuilder.append(" and a.dateCreated < :dateTo ");
            paramsMap.put("dateTo", filter.getDateCreatedTo().plusDays(1).atStartOfDay());
        }
    }

    private void addTempNumberSearch(StringBuilder queryBuilder, ApplicationCorrespondenceFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getTempNumber())){
            queryBuilder.append(" and lower(a.application.tempNumber) like :tempNumber ");
            paramsMap.put("tempNumber", "%"+filter.getTempNumber().toLowerCase()+"%");
        }
    }

    private void addRegistrationNumberDateSearch(StringBuilder queryBuilder, ApplicationCorrespondenceFilter filter, Map<String, Object> paramsMap){
        if(filter.getRegistrationDateFrom() != null){
            queryBuilder.append(" and a.registrationDateFrom >= :regDateFrom ");
            paramsMap.put("regDateFrom", filter.getRegistrationDateFrom().atStartOfDay());
        }

        if(filter.getRegistrationDateTo() != null){
            queryBuilder.append(" and a.registrationDateTo < :regDateTo ");
            paramsMap.put("regDateTo", filter.getRegistrationDateTo().plusDays(1).atStartOfDay());
        }
        if(StringUtils.hasText(filter.getRegistrationNumber())){
            queryBuilder.append(" and lower(a.registrationNumber) like :registrationNumber ");
            paramsMap.put("registrationNumber", "%"+filter.getRegistrationNumber().toLowerCase()+"%");
        }
    }

    private void addReadStatusDateSearch(StringBuilder queryBuilder, ApplicationCorrespondenceFilter filter, Map<String, Object> paramsMap){
        if(filter.getDateReadFrom() != null){
            queryBuilder.append(" and a.dateRead >= :dateReadFrom ");
            paramsMap.put("dateReadFrom", filter.getDateReadFrom().atStartOfDay());
        }

        if(filter.getDateReadTo() != null){
            queryBuilder.append(" and a.dateRead < :dateReadTo ");
            paramsMap.put("dateReadTo", filter.getDateReadTo().plusDays(1).atStartOfDay());
        }
        if(filter.getRead() != null){
            if(filter.getRead()) {
                queryBuilder.append(" and a.dateRead is not null ");
            } else {
                queryBuilder.append(" and a.dateRead is null ");
            }
        }
    }

    private String getDBOrderBy(String orderBy, String order){
        StringBuilder sb = new StringBuilder();
        switch (orderBy){
            case "tempNumber": return sb.append(" a.application.tempNumber ").append(order).toString();
            case "registrationNumber": return sb.append(" a.registrationNumber ").append(order).append(" NULLS LAST, a.registrationDate ").append(order).append(" NULLS LAST ").toString();
            case "dateRead": return sb.append(" a.dateRead ").append(order).toString();
            default: return sb.append(" a.dateCreated ").append(order).toString();
        }
    };

    private void setQueryParams( Map<String, Object> paramsMap, Query query){
        paramsMap.keySet().stream().forEach(
                key -> query.setParameter(key, paramsMap.get(key))
        );
    }
}
