package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionCalendarFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.sort.RudiApplicationsSortUtils;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedSpecialityEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VCommissionCalendarEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.query_result.CalendarProcessDataQR;
import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationRecognizedSpecialityRepository;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionCalendarRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommissionCalendarRepositoryCustomImpl extends BaseRepositoryCustomImpl implements CommissionCalendarRepositoryCustom {
    private final ApplicationRecognizedSpecialityRepository specialityRepository;

    @Override
    public List<VCommissionCalendarEntity> searchRecords(CommissionCalendarFilterDTO filter) {
        TypedQuery<VCommissionCalendarEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int getRecordsCount(CommissionCalendarFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    @Override
    public CalendarProcessDataQR getProcessData(Integer calendarId, Integer applicationId) {
        String buildQuery = buildProcessDataQuery();
        Query query = em.createNativeQuery(buildQuery);
        query.setParameter("calendarId", calendarId);
        query.setParameter("applicationId", applicationId);
        Object[] result = (Object[]) query.getSingleResult();

        List<String> specialities = new ArrayList<>();
        List<ApplicationRecognizedSpecialityEntity> applicationRecognizedSpecialityEntities = specialityRepository.selectByApplicationId(applicationId);
        if (!CollectionUtils.isEmpty(applicationRecognizedSpecialityEntities)) {
            applicationRecognizedSpecialityEntities.stream().forEach(r -> {
                specialities.add(r.getSpeciality());
            });
        }
        return new CalendarProcessDataQR(applicationId, calendarId, (String) result[0], (String) result[1], (String) result[2], (String) result[3], (Integer) result[4],
                (String) result[5], specialities,(String) result[6],(String) result[7],(String) result[8],(Integer) result[9],(String) result[10]);
    }

    private String buildProcessDataQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("select ca.motives,\n" +
                "       ca.applicant_info            as applicantInfo,\n" +
                "       ard.recognized_edu_level     as recognizedEduLevel\n" +
                "        ,\n" +
                "       ard.recognized_qualification as recognizedQualification,\n" +
                "       ard.recognized_prof_group_id as recognizedProfGroupId,\n" +
                "       app.status_code              as statusCode,\n" +
                "       rf1.name                     as recognizedEduLevelName,\n" +
                "       pg.name                      as recognizedProfGroupName,\n" +
                "       rf2.name                     as statusName,\n" +
                "       lr.id                        as legalReasonId,\n" +
                "       lr.name                     as legalReasonName\n" +
                "from common.application app\n" +
                "         inner join rudi.commission_applications ca on ca.apn_id = app.id and ca.calendar_id = :calendarId\n" +
                "         inner join rudi.rudi_application ra on ra.apn_id = app.id \n" +
                "         left join nomenclatures.legal_reason lr on lr.id = ra.legal_reason_id \n" +
                "         left join rudi.application_recognition_details ard on ard.apn_id = app.id\n" +
                "         left join nomenclatures.reference_data rf1\n" +
                "                   on rf1.domain = 'EDUCATION_LEVEL' and rf1.code = ard.recognized_edu_level\n" +
                "         left join nomenclatures.prof_group pg on pg.id = ard.recognized_prof_group_id\n" +
                "         left join nomenclatures.reference_data rf2 on rf2.domain = 'APPLICATION_STATUS' and rf2.code = app.status_code\n" +
                "where app.id = :applicationId");
        return builder.toString();
    }


    protected <T> TypedQuery<T> createQuery(CommissionCalendarFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(VCommissionCalendarEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");


        String sessionNumAsText = filter.getSessionNum();
        if (NumberUtils.isCreatable(sessionNumAsText)) {
            queryBuilder.append(" AND r.sessionNum = :sessionNum ");
            queryParameters.put("sessionNum", Integer.valueOf(sessionNumAsText));
        }

        if (StringUtils.hasText(sessionNumAsText) && !NumberUtils.isCreatable(sessionNumAsText)) {
            queryBuilder.append(" AND 1 != 1 ");
        }

        String sessionStatusCode = filter.getSessionStatusCode();
        if (StringUtils.hasText(sessionStatusCode)) {
            queryBuilder.append(" AND r.statusCode = :sessionStatusCode ");
            queryParameters.put("sessionStatusCode", sessionStatusCode);
        }


        if (Objects.nonNull(filter.getSessionTimeFrom())) {
            queryBuilder.append(" AND r.sessionTime >= :sessionTimeFrom");
            queryParameters.put("sessionTimeFrom", filter.getSessionTimeFrom().atStartOfDay());
        }

        if (Objects.nonNull(filter.getSessionTimeTo())) {
            queryBuilder.append(" AND r.sessionTime < :sessionTimeTo");
            queryParameters.put("sessionTimeTo", filter.getSessionTimeTo().plusDays(1).atStartOfDay());
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

        Class<? extends Serializable> queryClass = isCount ? Number.class : VCommissionCalendarEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
