package bg.duosoft.email.nacidemailproducer.repository.custom;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailTemplateMapper;
import bg.duosoft.email.nacidemailproducer.filter.EmailTemplateFilter;
import bg.duosoft.email.nacidemailproducer.filter.EmailTemplateSorterUtils;
import bg.duosoft.email.nacidemailproducer.filter.Sortable;
import bg.duosoft.email.nacidemailproducer.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmailTemplateRepositoryCustomImpl extends BaseRepositoryCustomImpl implements EmailTemplateRepositoryCustom {

    private final EmailTemplateMapper emailTemplateMapper;

    @Override
    public List<CEmailTemplate> selectEmailTemplates(EmailTemplateFilter filter) {
        String buildQuery = buildQuery(filter, false);
        Query query = em.createNativeQuery(buildQuery, EEmailTemplate.class);
        addQueryParams(filter, query);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return fillResult(query);
    }

    @Override
    public int selectEmailTemplatesCount(EmailTemplateFilter filter) {
        String buildQuery = buildQuery(filter, true);
        Query query = em.createNativeQuery(buildQuery);
        addQueryParams(filter, query);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    private String buildQuery(EmailTemplateFilter filter, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(*) " : " * ");
        queryBuilder.append(" FROM emails.email_template n ");
        queryBuilder.append(" WHERE 1=1 ");

        if (Objects.nonNull(filter.getCreatedDateFrom())) {
            queryBuilder.append(" AND n.created_date >= :createdDateFrom ");
        }
        if (Objects.nonNull(filter.getCreatedDateTo())) {
            queryBuilder.append(" AND n.created_date <= :createdDateTo ");
        }
        if (Objects.nonNull(filter.getLastUpdateDateFrom())) {
            queryBuilder.append(" AND n.last_update_date >= :lastUpdateDateFrom ");
        }
        if (Objects.nonNull(filter.getLastUpdateDateTo())) {
            queryBuilder.append(" AND n.last_update_date <= :lastUpdateDateTo ");
        }
        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND LOWER(n.name) like LOWER(:name) ");
        }
        if (StringUtils.hasText(filter.getNameEn())) {
            queryBuilder.append(" AND LOWER(n.name_en) like LOWER(:nameEn) ");
        }
        if (StringUtils.hasText(filter.getSubject())) {
            queryBuilder.append(" AND LOWER(n.subject) like LOWER(:subject) ");
        }
        if (StringUtils.hasText(filter.getText())) {
            queryBuilder.append(" AND LOWER(n.text) like LOWER(:text) ");
        }
        if (Objects.nonNull(filter.getIsHtml())) {
            queryBuilder.append(" AND n.is_html = :isHtml ");
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }
            String[] columns = EmailTemplateSorterUtils.sorterColumnMap().get(sortColumn).split(",");
            String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
            queryBuilder.append(" ORDER BY ").append(order);
        }
        return queryBuilder.toString();
    }

    private void addQueryParams(EmailTemplateFilter filter, Query query) {
        Date createdDateFrom = filter.getCreatedDateFrom();
        if (Objects.nonNull(createdDateFrom)) {
            query.setParameter("createdDateFrom", createdDateFrom);
        }
        Date createdDateTo = filter.getCreatedDateTo();
        if (Objects.nonNull(createdDateTo)) {
            query.setParameter("createdDateTo", DateUtils.toTheEndOfTheDay(createdDateTo));
        }
        Date lastUpdateDateFrom = filter.getLastUpdateDateFrom();
        if (Objects.nonNull(lastUpdateDateFrom)) {
            query.setParameter("lastUpdateDateFrom", lastUpdateDateFrom);
        }
        Date lastUpdateDateTo = filter.getLastUpdateDateTo();
        if (Objects.nonNull(lastUpdateDateTo)) {
            query.setParameter("lastUpdateDateTo", DateUtils.toTheEndOfTheDay(lastUpdateDateTo));
        }
        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            query.setParameter("name", "%" + name + "%");
        }
        String nameEn = filter.getNameEn();
        if (StringUtils.hasText(nameEn)) {
            query.setParameter("nameEn", "%" + nameEn + "%");
        }
        String subject = filter.getSubject();
        if (StringUtils.hasText(subject)) {
            query.setParameter("subject", "%" + subject + "%");
        }
        String text = filter.getText();
        if (StringUtils.hasText(text)) {
            query.setParameter("text", "%" + text + "%");
        }
        Boolean isHtml = filter.getIsHtml();
        if (Objects.nonNull(isHtml)) {
            query.setParameter("isHtml", isHtml);
        }
    }

    private List<CEmailTemplate> fillResult(Query query) {
        List<EEmailTemplate> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return emailTemplateMapper.toCoreList(resultList);
    }

}
