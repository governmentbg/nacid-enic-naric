package bg.duosoft.email.nacidemailproducer.repository.custom;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailNotificationMapper;
import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationStatus;
import bg.duosoft.email.nacidemailproducer.filter.EmailNotificationFilter;
import bg.duosoft.email.nacidemailproducer.filter.EmailNotificationSorterUtils;
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
public class EmailNotificationRepositoryCustomImpl extends BaseRepositoryCustomImpl implements EmailNotificationRepositoryCustom {

    private final EmailNotificationMapper emailNotificationMapper;

    @Override
    public List<CEmailNotification> selectEmailNotifications(EmailNotificationFilter filter) {
        String buildQuery = buildQuery(filter, false);
        Query query = em.createNativeQuery(buildQuery, EEmailNotification.class);
        addQueryParams(filter, query);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return fillResult(query);
    }

    @Override
    public int selectEmailNotificationsCount(EmailNotificationFilter filter) {
        String buildQuery = buildQuery(filter, true);
        Query query = em.createNativeQuery(buildQuery);
        addQueryParams(filter, query);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    private String buildQuery(EmailNotificationFilter filter, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(*) " : " * ");
        queryBuilder.append(" FROM emails.email_notification n ");
        queryBuilder.append(" WHERE 1=1 ");

        if (Objects.nonNull(filter.getCreatedDateFrom())) {
            queryBuilder.append(" AND n.created_date >= :createdDateFrom ");
        }
        if (Objects.nonNull(filter.getCreatedDateTo())) {
            queryBuilder.append(" AND n.created_date <= :createdDateTo ");
        }
        if (Objects.nonNull(filter.getSentDateFrom())) {
            queryBuilder.append(" AND n.sent_date >= :sentDateFrom ");
        }
        if (Objects.nonNull(filter.getSentDateTo())) {
            queryBuilder.append(" AND n.sent_date <= :sentDateTo ");
        }
        if (StringUtils.hasText(filter.getRecipients())) {
            queryBuilder.append(" AND LOWER(n.recipients) like LOWER(:recipients) ");
        }
        if (StringUtils.hasText(filter.getStatus())) {
            EmailNotificationStatus emailNotificationStatus = EmailNotificationStatus.valueOf(filter.getStatus());
            switch (emailNotificationStatus) {
                case SENT:
                    queryBuilder.append(" AND n.sent_date IS NOT NULL ");
                    break;
                case NOT_SENT:
                    queryBuilder.append(" AND n.sent_date IS NULL AND n.skip_sending = true ");
                    break;
                case IN_SENDING_PROCESS:
                    queryBuilder.append(" AND n.sent_date IS NULL AND n.skip_sending = false ");
                    break;
            }
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }
            String[] columns = EmailNotificationSorterUtils.sorterColumnMap().get(sortColumn).split(",");
            String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
            queryBuilder.append(" ORDER BY ").append(order);
        }
        return queryBuilder.toString();
    }

    private void addQueryParams(EmailNotificationFilter filter, Query query) {
        Date createdDateFrom = filter.getCreatedDateFrom();
        if (Objects.nonNull(createdDateFrom)) {
            query.setParameter("createdDateFrom", createdDateFrom);
        }
        Date createdDateTo = filter.getCreatedDateTo();
        if (Objects.nonNull(createdDateTo)) {
            query.setParameter("createdDateTo", DateUtils.toTheEndOfTheDay(createdDateTo));
        }
        Date sentDateFrom = filter.getSentDateFrom();
        if (Objects.nonNull(sentDateFrom)) {
            query.setParameter("sentDateFrom", sentDateFrom);
        }
        Date sentDateTo = filter.getSentDateTo();
        if (Objects.nonNull(sentDateTo)) {
            query.setParameter("sentDateTo", DateUtils.toTheEndOfTheDay(sentDateTo));
        }
        String recipients = filter.getRecipients();
        if (StringUtils.hasText(recipients)) {
            query.setParameter("recipients", "%" + recipients + "%");
        }
    }

    private List<CEmailNotification> fillResult(Query query) {
        List<EEmailNotification> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return emailNotificationMapper.toCoreList(resultList);
    }

}
