package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.RudiApplicationsFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.sort.RudiApplicationsSortUtils;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.ApplicationsRepositoryCustom;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ApplicationsRepositoryCustomImpl extends BaseRepositoryCustomImpl implements ApplicationsRepositoryCustom {
    @Override
    public List<VRudiApplicationsEntity> searchRecords(RudiApplicationsFilterDTO filter) {
        TypedQuery<VRudiApplicationsEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<Integer> selectApplicationIds(RudiApplicationsFilterDTO filter) {
        TypedQuery<Integer> query = createApplicationIdsQuery(filter);
        return query.getResultList();
    }

    @Override
    public List<VRudiApplicationsEntity> selectApplicationsByIdsAndSort(List<Integer> ids, String sortColumn, Boolean ascOrder) {
        TypedQuery<VRudiApplicationsEntity> query = createQuery(ids, sortColumn, ascOrder);
        return query.getResultList();
    }


    @Override
    public int getRecordsCount(RudiApplicationsFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(RudiApplicationsFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(VRudiApplicationsEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");
        filterQuery(filter, isCount, queryParameters, queryBuilder);
        Class<? extends Serializable> queryClass = isCount ? Number.class : VRudiApplicationsEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    protected TypedQuery<Integer> createApplicationIdsQuery(RudiApplicationsFilterDTO filter) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT r.id");
        queryBuilder.append(" FROM ").append(VRudiApplicationsEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");
        filterQuery(filter, false, queryParameters, queryBuilder);
        TypedQuery<Integer> typedQuery = em.createQuery(queryBuilder.toString(), Integer.class);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    private static void filterQuery(RudiApplicationsFilterDTO filter, boolean isCount, Map<String, Object> queryParameters, StringBuilder queryBuilder) {
        String docflowStatusCode = filter.getDocflowStatusCode();
        if (StringUtils.hasText(docflowStatusCode)) {
            queryBuilder.append(" AND r.docflowStatusCode = :docflowStatusCode ");
            queryParameters.put("docflowStatusCode", docflowStatusCode);
        }

        String apnStatusCode = filter.getApnStatusCode();
        if (StringUtils.hasText(apnStatusCode)) {
            queryBuilder.append(" AND r.apnStatusCode = :apnStatusCode ");
            queryParameters.put("apnStatusCode", apnStatusCode);
        }

        Integer universityId = filter.getUniversityId();
        if (Objects.nonNull(universityId)) {
            queryBuilder.append(" AND r.universityId = :universityId ");
            queryParameters.put("universityId", universityId);
        }


        String entryNum = filter.getEntryNum();
        Boolean entryNumExactMatch = filter.getEntryNumExactMatch();
        if (StringUtils.hasText(entryNum)) {
            queryBuilder.append(" AND LOWER(r.entryNum) like LOWER(:entryNum) ");
            if (Objects.nonNull(entryNumExactMatch) && entryNumExactMatch) {
                queryParameters.put("entryNum", entryNum);
            } else {
                queryParameters.put("entryNum", "%" + entryNum + "%");
            }
        }

        String universityName = filter.getUniversityName();
        if (StringUtils.hasText(universityName)) {
            queryBuilder.append(" AND LOWER(r.universityName) like LOWER(:universityName) ");
            queryParameters.put("universityName", "%" + universityName + "%");
        }

        String responsibleUserName = filter.getResponsibleUser();
        if (StringUtils.hasText(responsibleUserName)) {
            if (responsibleUserName.equals("-")) {
                queryBuilder.append(" AND r.responsibleUserName is null ");
            } else {
                queryBuilder.append(" AND LOWER(r.responsibleUserName) like LOWER(:responsibleUserName) ");
                queryParameters.put("responsibleUserName", "%" + responsibleUserName + "%");
            }
        }

        String filingType = filter.getFilingType();
        if (StringUtils.hasText(filingType)) {
            switch (filingType) {
                case "D" -> queryBuilder.append(" AND r.efilingId is null ");
                case "EL" -> queryBuilder.append(" AND r.efilingId is not null ");
            }
        }

        String applicantName = filter.getApplicantName();
        if (StringUtils.hasText(applicantName)) {
            String q = generateTextSearchSqlByValueAndSearchType(applicantName, filter.getApplicantNameSearchType(), "r.applicantName", "applicantName", queryParameters);
            queryBuilder.append(" AND " + q);
        }

        String diplomaOwnerName = filter.getDiplomaOwnerName();
        if (StringUtils.hasText(diplomaOwnerName)) {
            String q = generateTextSearchSqlByValueAndSearchType(diplomaOwnerName, filter.getDiplomaOwnerNameSearchType(), "r.diplomaOwnerName", "diplomaOwnerName", queryParameters);
            queryBuilder.append(" AND " + q);
        }

        LocalDate dateFrom = filter.getDateFrom();
        if (Objects.nonNull(dateFrom)) {
            queryBuilder.append(" AND r.entryDate >= :dateFrom ");
            queryParameters.put("dateFrom", dateFrom);
        }

        LocalDate dateTo = filter.getDateTo();
        if (Objects.nonNull(dateTo)) {
            queryBuilder.append(" AND r.entryDate <= :dateTo ");
            queryParameters.put("dateTo", dateTo);
        }

        LocalDate backofficeDateFrom = filter.getBackofficeDateFrom();
        if (Objects.nonNull(backofficeDateFrom)) {
            queryBuilder.append(" AND r.backofficeDate >= :backofficeDateFrom ");
            queryParameters.put("backofficeDateFrom", backofficeDateFrom);
        }

        LocalDate backofficeDateTo = filter.getBackofficeDateTo();
        if (Objects.nonNull(backofficeDateTo)) {
            queryBuilder.append(" AND r.backofficeDate <= :backofficeDateTo ");
            queryParameters.put("backofficeDateTo", backofficeDateTo);
        }

        String ateCode = filter.getAteCode();
        if (StringUtils.hasText(ateCode)) {
            queryBuilder.append(" AND r.ateCode = :ateCode ");
            queryParameters.put("ateCode", ateCode);
        }

        String aseCode = filter.getAseCode();
        if (StringUtils.hasText(aseCode)) {
            queryBuilder.append(" AND r.aseCode = :aseCode ");
            queryParameters.put("aseCode", aseCode);
        }

        List<Integer> excludedApplications = filter.getExcludedApplications();

        if (!CollectionUtils.isEmpty(excludedApplications)) {
            queryBuilder.append(" AND r.id not in (:excludedApplications) ");
            queryParameters.put("excludedApplications", excludedApplications);
        }
        if (!ObjectUtils.isEmpty(filter.getApplicantCivilId())) {
            queryBuilder.append(" AND lower(r.applicantCivilId) like lower( :applicantCivilId ) ");
            queryParameters.put("applicantCivilId", "%" + filter.getApplicantCivilId() + "%");
        }
        if (!ObjectUtils.isEmpty(filter.getUniversityCountryCode())) {
            queryBuilder.append(" AND r.universityCountryCode = :universityCountryCode " );
            queryParameters.put("universityCountryCode", filter.getUniversityCountryCode());
        }
        if (!ObjectUtils.isEmpty(filter.getDiplomaOwnerCivilId())) {
            queryBuilder.append(" AND lower(r.diplomaOwnerCivilId) like lower( :diplomaOwnerCivilId ) ");
            queryParameters.put("diplomaOwnerCivilId", "%" + filter.getDiplomaOwnerCivilId() + "%");
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }

            String sortFields = RudiApplicationsSortUtils.sorterColumnMap().get(sortColumn);
            if (StringUtils.hasText(sortFields)) {
                String[] columns = sortFields.split(",");
                String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }
    }


    protected <T> TypedQuery<T> createQuery(List<Integer> ids, String sortColumn, Boolean ascOrder) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT r ");
        queryBuilder.append(" FROM ").append(VRudiApplicationsEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        if (!CollectionUtils.isEmpty(ids)) {
            queryBuilder.append(" AND r.id in (:ids) ");
            queryParameters.put("ids", ids);
        }

        if (StringUtils.hasText(sortColumn) && Objects.nonNull(ascOrder)) {
            queryBuilder.append(" ORDER BY r.").append(sortColumn);
            if (ascOrder) {
                queryBuilder.append(" ASC");
            } else {
                queryBuilder.append(" DESC");
            }
        }


        Class<? extends Serializable> queryClass = VRudiApplicationsEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
