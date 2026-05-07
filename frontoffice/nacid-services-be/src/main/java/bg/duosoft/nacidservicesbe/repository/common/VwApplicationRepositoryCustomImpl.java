package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationFilter;
import bg.duosoft.nacidservicesbe.domain.entity.common.VwApplicationEntity;
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
 * Date: 21.12.2022
 * Time: 16:00
 */
public class VwApplicationRepositoryCustomImpl implements VwApplicationRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<VwApplicationEntity> filterApplications(ApplicationFilter filter) {
        Map<String, Object> paramsMap = new HashMap<>();
        TypedQuery<VwApplicationEntity> query = em.createQuery(getQueryAndSetParams(filter, false, paramsMap), VwApplicationEntity.class);
        setQueryParams(paramsMap, query);

        query.setMaxResults(filter.getPageSize());
        query.setFirstResult(filter.getPage()*filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countFilteredApplications(ApplicationFilter filter) {
        Map<String, Object> paramsMap = new HashMap<>();
        TypedQuery<Long> query = em.createQuery(getQueryAndSetParams(filter, true, paramsMap), Long.class);
        setQueryParams(paramsMap, query);
        return query.getSingleResult().intValue();
    }

    private String getQueryAndSetParams(ApplicationFilter filter, boolean isCount, Map<String, Object> paramsMap){
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount? " count(a) ": " a ")
                .append(" FROM ").append(VwApplicationEntity.class.getSimpleName()).append(" a ")
                .append(" WHERE 1 = 1 ");

        addUserSearch(queryBuilder, filter, paramsMap);
        addDateCreatedSearch(queryBuilder, filter, paramsMap);
        addStatusTempNumberSearch(queryBuilder, filter, paramsMap);
        addEntryNumberDateSearch(queryBuilder, filter, paramsMap)  ;
        addAppTypeSubtypeSearch(queryBuilder, filter, paramsMap);
        addSARSearch(queryBuilder, filter, paramsMap);
        addLastSubmissionDateSearch(queryBuilder, filter, paramsMap);
        addApplicantNameSearch(queryBuilder, filter, paramsMap);
        addSignedPaidSearch(queryBuilder, filter, paramsMap);

        if(!isCount && filter.getOrder() != null && filter.getOrderBy() != null){
            queryBuilder.append(" ORDER BY ").append(getDBOrderBy(filter.getOrderBy(), (filter.getOrder().equals("asc")? " asc ": " desc ")));
        }

        return queryBuilder.toString();
    }

    private void addUserSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getUser())){
            queryBuilder.append(" and a.userCreated = :user ");
            paramsMap.put("user", filter.getUser());
        }
    }

    private void addDateCreatedSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(filter.getDateCreatedFrom() != null){
            queryBuilder.append(" and a.dateCreated >= :dateFrom ");
            paramsMap.put("dateFrom", filter.getDateCreatedFrom().atStartOfDay());
        }

        if(filter.getDateCreatedTo() != null){
            queryBuilder.append(" and a.dateCreated < :dateTo ");
            paramsMap.put("dateTo", filter.getDateCreatedTo().plusDays(1).atStartOfDay());
        }
    }

    private void addStatusTempNumberSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(filter.getFoStatusCodes() != null && filter.getFoStatusCodes().size() > 0){
            queryBuilder.append(" and a.foStatusCode in (:foStatusCodes) ");
            paramsMap.put("foStatusCodes", filter.getFoStatusCodes());
        } else if(filter.getFoStatusCodesExclude() != null && filter.getFoStatusCodesExclude().size() > 0){
            queryBuilder.append(" and a.foStatusCode not in (:foStatusCodesExclude) ");
            paramsMap.put("foStatusCodesExclude", filter.getFoStatusCodesExclude());
        }

        if(StringUtils.hasText(filter.getLastStatusName())){
            queryBuilder.append(" and a.lastStatusName = :lastStatusName ");
            paramsMap.put("lastStatusName", filter.getLastStatusName());
        }

        if(StringUtils.hasText(filter.getTempNumber())){
            queryBuilder.append(" and lower(a.tempNumber) like :tempNumber ");
            paramsMap.put("tempNumber", "%"+filter.getTempNumber().toLowerCase()+"%");
        }
    }

    private void addEntryNumberDateSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getEntryNumber())){
            queryBuilder.append(" and lower(a.entryNumber) like :entryNumber ");
            paramsMap.put("entryNumber", "%"+filter.getEntryNumber().toLowerCase()+"%");
        }
        if(filter.getEntryDateFrom() != null){
            queryBuilder.append(" and a.entryDate >= :entryDateFrom ");
            paramsMap.put("entryDateFrom", filter.getEntryDateFrom());
        }

        if(filter.getEntryDateTo() != null){
            queryBuilder.append(" and a.entryDate <= :entryDateTo ");
            paramsMap.put("entryDateTo", filter.getEntryDateTo());
        }
    }

    private void addAppTypeSubtypeSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getApplicationTypeCode())){
            queryBuilder.append(" and a.applicationTypeCode = :typeCode ");
            paramsMap.put("typeCode", filter.getApplicationTypeCode());
        }

        if(StringUtils.hasText(filter.getApplicationSubtypeCode())){
            queryBuilder.append(" and a.applicationSubtypeCode = :subtypeCode ");
            paramsMap.put("subtypeCode", filter.getApplicationSubtypeCode());
        }
    }

    private void addSARSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(filter.isApplicationSAR()) {
            if (Boolean.TRUE.equals(filter.getStatute())) {
                queryBuilder.append(" and a.sarApplication.statuteFlag = 1 ");
            }
            if (Boolean.TRUE.equals(filter.getAuthenticity())) {
                queryBuilder.append(" and a.sarApplication.authenticityFlag = 1 ");
            }
            if (Boolean.TRUE.equals(filter.getRecommendation())) {
                queryBuilder.append(" and a.sarApplication.recommendationFlag = 1 ");
            }
        }
    }

    private void addLastSubmissionDateSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(filter.getDateLastSubmittedFrom() != null){
            queryBuilder.append(" and a.lastSubmissionDate >= :lastSubmissionDateFrom ");
            paramsMap.put("lastSubmissionDateFrom", filter.getDateLastSubmittedFrom().atStartOfDay());
        }

        if(filter.getDateLastSubmittedTo() != null){
            queryBuilder.append(" and a.lastSubmissionDate < :lastSubmissionDateTo ");
            paramsMap.put("lastSubmissionDateTo", filter.getDateLastSubmittedTo().plusDays(1).atStartOfDay());
        }
    }

    private void addApplicantNameSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap){
        if(StringUtils.hasText(filter.getApplicantName())){
            queryBuilder.append(" and lower(a.applicantName) like :applicantName ");
            paramsMap.put("applicantName", "%"+filter.getApplicantName().toLowerCase()+"%");
        }
    }

    private void addSignedPaidSearch(StringBuilder queryBuilder, ApplicationFilter filter, Map<String, Object> paramsMap) {
        if(Boolean.TRUE.equals(filter.getSigned())){
            queryBuilder.append(" AND a.signedFlag = 1");
        }
        if(Boolean.TRUE.equals(filter.getPaid())){
            queryBuilder.append(" AND a.paidFlag = 1");
        }
    }

    private void setQueryParams( Map<String, Object> paramsMap, Query query){
        paramsMap.keySet().stream().forEach(
                key -> query.setParameter(key, paramsMap.get(key))
        );
    }

    private String getDBOrderBy(String orderBy, String order){
        StringBuilder sb = new StringBuilder();
        switch (orderBy){
            case "tempNumber": return sb.append(" a.tempNumber ").append(order).toString();
            case "entryNumber": return sb.append(" a.entryNumber ").append(order).append(" NULLS LAST, a.entryDate ").append(order).append(" NULLS LAST ").toString();
            case "foStatusName": return sb.append(" a.foStatusName ").append(order).toString();
            case "applicantName": return sb.append(" a.applicantName ").append(order).toString();
            case "dateCreated": return sb.append(" a.dateCreated ").append(order).toString();

            default: return sb.append(" a.lastSubmissionDate ").append(order).append(" NULLS LAST, a.dateCreated ").append(order).append(" NULLS LAST ").toString();
        }
    };
}
