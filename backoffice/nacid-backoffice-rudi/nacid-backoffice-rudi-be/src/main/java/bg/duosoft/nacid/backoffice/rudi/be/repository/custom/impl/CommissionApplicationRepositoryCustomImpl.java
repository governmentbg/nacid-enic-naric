package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiCommissionApplicationsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionApplicationRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
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
public class CommissionApplicationRepositoryCustomImpl extends BaseRepositoryCustomImpl implements CommissionApplicationRepositoryCustom {
    @Override
    public List<VRudiCommissionApplicationsEntity> selectApplicationsByCalendarAndAppId(List<Integer> ids, Integer calendarId, String sortColumn, Boolean ascOrder) {
        TypedQuery<VRudiCommissionApplicationsEntity> query = createQuery(ids,calendarId, sortColumn, ascOrder);
        return query.getResultList();
    }


    protected <T> TypedQuery<T> createQuery(List<Integer> ids, Integer calendarId, String sortColumn, Boolean ascOrder) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT r ");
        queryBuilder.append(" FROM ").append(VRudiCommissionApplicationsEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        if (!CollectionUtils.isEmpty(ids)) {
            queryBuilder.append(" AND r.pk.id in (:ids) ");
            queryParameters.put("ids", ids);
        }

        if (Objects.nonNull(calendarId)) {
            queryBuilder.append(" AND r.pk.calendarId =:calendarId ");
            queryParameters.put("calendarId", calendarId);
        }

        if (StringUtils.hasText(sortColumn) && Objects.nonNull(ascOrder)) {
            queryBuilder.append(" ORDER BY r.").append(sortColumn);
            if (ascOrder) {
                queryBuilder.append(" ASC");
            } else {
                queryBuilder.append(" DESC");
            }
        }


        Class<? extends Serializable> queryClass = VRudiCommissionApplicationsEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
