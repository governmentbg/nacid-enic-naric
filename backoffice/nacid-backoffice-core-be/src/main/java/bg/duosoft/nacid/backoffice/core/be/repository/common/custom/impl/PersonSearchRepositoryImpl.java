package bg.duosoft.nacid.backoffice.core.be.repository.common.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.PersonSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.LegalEntityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.PersonType;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import bg.duosoft.nacidbackofficeshareddata.utils.NomenclatureSearchQueryUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PersonSearchRepositoryImpl extends BaseRepositoryCustomImpl implements PersonSearchRepository {

    @Override
    public List<Object[]> searchForApplicationsUse(PersonSearchDTO searchCriteria, int maxResults) {
        Map<String, Object> queryParameters = new HashMap<>();
        String queryString = buildQuery(searchCriteria, queryParameters);
        Query query = em.createNativeQuery(queryString);
        query.setFirstResult(0);
        query.setMaxResults(maxResults);
        queryParameters.keySet().forEach(key -> query.setParameter(key, queryParameters.get(key)));
        return query.getResultList();
    }

    private String buildQuery(PersonSearchDTO searchCriteria, Map<String, Object> queryParameters) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT " +
                "       p.id as personId," +
                "       p.first_name," +
                "       p.second_name," +
                "       p.last_name," +
                "       p.civil_id," +
                "       p.civil_id_type," +
                "       p.legal_name," +
                "       p.legal_type," +
                "       p.legal_nature_type," +
                "       p.origin_country," +
                "       p.citizenship_id," +
                "       p.email," +
                "       s.name," +
                "       p.origin_city," +
                "       p.active," +
                "       p.phone ");
        builder.append(" FROM common.person p  ");
        builder.append(" LEFT JOIN nomenclatures.ek_settlement s on s.code = p.origin_set_code ");
        builder.append(" WHERE 1=1 ");

        PersonType personType = PersonType.selectByCode(searchCriteria.getPersonType());
        switch (personType) {
            case NATURAL_PERSON -> builder.append("AND p.legal_type = 'NP' ");
            case COMPANY -> builder.append("AND p.legal_type = 'LE' AND p.legal_nature_type = 'C' ");
            case UNIVERSITY -> builder.append("AND p.legal_type = 'LE' AND p.legal_nature_type = 'U' ");
        }

        String name = searchCriteria.getName();
        if (StringUtils.hasText(name)) {
            switch (personType) {
                case NATURAL_PERSON ->
                        builder.append(" AND ( REPLACE(CONCAT(LOWER(p.first_name), LOWER(p.second_name), LOWER(p.last_name)), ' ','') like LOWER(:name) )");
                case COMPANY, UNIVERSITY ->
                        builder.append(" AND (REPLACE(LOWER(p.legal_name), ' ','') like LOWER(:name) )");
            }
            queryParameters.put("name", "%" + name.replace(" ", "") + "%");
        }

        String civilId = searchCriteria.getCivilId();
        if (StringUtils.hasText(civilId)) {
            builder.append(" AND (LOWER(p.civil_id) like LOWER(:civilId)) ");
            queryParameters.put("civilId", "%" + civilId + "%");
        }

        String civilIdType = searchCriteria.getCivilIdType();
        if (StringUtils.hasText(civilIdType)) {
            builder.append(" AND (p.civil_id_type = :civilIdType) ");
            queryParameters.put("civilIdType", civilIdType);
        }

        String humanitarianStatus = searchCriteria.getHumanitarianStatus();
        if (StringUtils.hasText(humanitarianStatus)) {
            builder.append(" AND (p.humanitarian_status_code = :humanitarianStatus) ");
            queryParameters.put("humanitarianStatus", humanitarianStatus);
        }

        StringIdDTO citizenship = searchCriteria.getCitizenship();
        if (Objects.nonNull(citizenship) && StringUtils.hasText(citizenship.getId())) {
            builder.append(" AND (p.citizenship_id = :citizenshipId) ");
            queryParameters.put("citizenshipId", citizenship.getId());
        }

        StringIdDTO originCountry = searchCriteria.getOriginCountry();
        if (Objects.nonNull(originCountry) && StringUtils.hasText(originCountry.getId())) {
            builder.append(" AND (p.origin_country = :originCountryId) ");
            queryParameters.put("originCountryId", originCountry.getId());
        }

        String originCity = searchCriteria.getOriginCity();
        if (StringUtils.hasText(originCity)) {
            builder.append(" AND ( LOWER(p.origin_city) like LOWER(:originCity) OR LOWER(s.name) like LOWER(:originCity) ) ");
            queryParameters.put("originCity", "%" + originCity + "%");
        }

        String email = searchCriteria.getEmail();
        if (StringUtils.hasText(email)) {
            builder.append(" AND (LOWER(p.email) like LOWER(:email) ) ");
            queryParameters.put("email", "%" + email + "%");
        }

        String phone = searchCriteria.getPhone();
        if (StringUtils.hasText(phone)) {
            builder.append(" AND (LOWER(p.phone) like LOWER(:phone) ) ");
            queryParameters.put("phone", "%" + phone + "%");
        }

        Boolean onlyActive = searchCriteria.getOnlyActive();
        if (BooleanUtils.isTrue(onlyActive)) {
            builder.append(" AND p.active = 1 ");
        }

        return builder.toString();
    }

    @Override
    public List<PersonEntity> searchLegalApplicants(LegalEntityFilterDTO filter) {
        TypedQuery<PersonEntity> query = createSearchLegalApplicantsQuery(filter);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<PersonEntity> searchLegalEntities(LegalEntityFilterDTO filter) {
        TypedQuery<PersonEntity> query = createSearchLegalEntitiesQuery(filter);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<PersonEntity> searchRepresentativeCompanies(LegalEntityFilterDTO filter) {
        TypedQuery<PersonEntity> query = createSearchRepresentativeCompaniesQuery(filter);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    protected TypedQuery<PersonEntity> createSearchRepresentativeCompaniesQuery(LegalEntityFilterDTO filter) {
        Map<String, Object> queryParameters = new HashMap<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(" DISTINCT(a.representativeCompany) ");
        queryBuilder.append(" FROM ").append(ApplicationEntity.class.getSimpleName()).append(" a ");
        queryBuilder.append(" WHERE 1=1 ");

        String legalName = filter.getLegalName();
        if (StringUtils.hasText(legalName)) {
            queryBuilder.append(" AND LOWER(a.representativeCompany.legalName) like LOWER(:legalName) ");
            queryParameters.put("legalName", "%" + legalName + "%");
        }

        queryBuilder.append("ORDER BY a.representativeCompany.legalName asc ");

        TypedQuery<PersonEntity> typedQuery = em.createQuery(queryBuilder.toString(), PersonEntity.class);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    protected TypedQuery<PersonEntity> createSearchLegalEntitiesQuery(LegalEntityFilterDTO filter) {
        Map<String, Object> queryParameters = new HashMap<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(" DISTINCT(p) ");
        queryBuilder.append(" FROM ").append(PersonEntity.class.getSimpleName()).append(" p ");
        queryBuilder.append(" WHERE 1=1 ");
        queryBuilder.append(" AND p.legalType.pk.id = '").append(LegalType.LEGAL_ENTITY.code()).append("' ");

        List<String> legalNatures = filter.getLegalNatureTypes();
        if (!CollectionUtils.isEmpty(legalNatures)) {
            queryBuilder.append(" AND p.legalNatureType.pk.id in (:legalNatures) ");
            queryParameters.put("legalNatures", legalNatures);
        }

        String legalName = filter.getLegalName();
        if (StringUtils.hasText(legalName)) {
            queryBuilder.append(" AND LOWER(p.legalName) like LOWER(:legalName) ");
            queryParameters.put("legalName", "%" + legalName + "%");
        }

        queryBuilder.append("ORDER BY p.legalName asc ");

        TypedQuery<PersonEntity> typedQuery = em.createQuery(queryBuilder.toString(), PersonEntity.class);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    protected TypedQuery<PersonEntity> createSearchLegalApplicantsQuery(LegalEntityFilterDTO filter) {
        Map<String, Object> queryParameters = new HashMap<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(" DISTINCT(a.applicant) ");
        queryBuilder.append(" FROM ").append(ApplicationEntity.class.getSimpleName()).append(" a ");
        queryBuilder.append(" WHERE 1=1 ");
        queryBuilder.append(" AND a.applicant.legalType.pk.id = '").append(LegalType.LEGAL_ENTITY.code()).append("' ");

        List<String> legalNatures = filter.getLegalNatureTypes();
        if (!CollectionUtils.isEmpty(legalNatures)) {
            queryBuilder.append(" AND a.applicant.legalNatureType.pk.id in (:legalNatures) ");
            queryParameters.put("legalNatures", legalNatures);
        }

        String legalName = filter.getLegalName();
        if (StringUtils.hasText(legalName)) {
            queryBuilder.append(" AND LOWER(a.applicant.legalName) like LOWER(:legalName) ");
            queryParameters.put("legalName", "%" + legalName + "%");
        }

        queryBuilder.append("ORDER BY a.applicant.legalName asc ");

        TypedQuery<PersonEntity> typedQuery = em.createQuery(queryBuilder.toString(), PersonEntity.class);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }

    @Override
    public List<PersonEntity> searchRecords(PersonFilterDTO filter) {
        TypedQuery<PersonEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    public int getRecordsCount(PersonFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(PersonFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(PersonEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" LEFT JOIN ").append(EkSettlementEntity.class.getSimpleName()).append(" s on s.id = r.originSettlement.id ");
        queryBuilder.append(" WHERE 1=1 ");

        String personType = filter.getPersonType();
        if (StringUtils.hasText(personType)) {
            if (personType.equals(PersonType.NATURAL_PERSON.code())) {
                queryBuilder.append(" AND r.legalType.pk.id = 'NP' ");
            } else if (personType.equals(PersonType.COMPANY.code())) {
                queryBuilder.append(" AND r.legalType.pk.id = 'LE' AND r.legalNatureType.pk.id = 'C' ");
            } else if (personType.equals(PersonType.UNIVERSITY.code())) {
                queryBuilder.append(" AND r.legalType.pk.id = 'LE' AND r.legalNatureType.pk.id = 'U' ");
            }
        }

        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            queryBuilder.append(" AND (concat(LOWER(r.firstName), ' ', LOWER(r.secondName), ' ', LOWER(r.lastName)) like LOWER(:name) OR " +
                    "concat(LOWER(r.firstName), ' ', LOWER(r.lastName)) like LOWER(:name) OR " +
                    "concat(LOWER(r.secondName), ' ', LOWER(r.lastName)) like LOWER(:name) OR " +
                    "concat(LOWER(r.firstName), ' ', LOWER(r.secondName)) like LOWER(:name) OR " +
                    "LOWER(r.firstName) like LOWER(:name) OR " +
                    "LOWER(r.secondName) like LOWER(:name) OR " +
                    "LOWER(r.lastName) like LOWER(:name) OR " +
                    "LOWER(r.legalName) like LOWER(:name)) ");
            queryParameters.put("name", "%" + name + "%");
        }

        String civilId = filter.getCivilId();
        if (StringUtils.hasText(civilId)) {
            queryBuilder.append(" AND (LOWER(r.civilId) like LOWER(:civilId)) ");
            queryParameters.put("civilId", "%" + civilId + "%");
        }

        String originCountry = filter.getCountryCode();
        if (StringUtils.hasText(originCountry)) {
            queryBuilder.append(" AND (r.originCountry.id = :originCountryId) ");
            queryParameters.put("originCountryId", originCountry);
        }

        String originCity = filter.getOriginCity();
        if (StringUtils.hasText(originCity)) {
            queryBuilder.append(" AND ( LOWER(r.originCity) like LOWER(:originCity) OR LOWER(s.name) like LOWER(:originCity) ) ");
            queryParameters.put("originCity", "%" + originCity + "%");
        }

        NomenclatureSearchQueryUtils.orderQuery(filter, isCount, queryBuilder);

        Class<? extends Serializable> queryClass = isCount ? Number.class : PersonEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
