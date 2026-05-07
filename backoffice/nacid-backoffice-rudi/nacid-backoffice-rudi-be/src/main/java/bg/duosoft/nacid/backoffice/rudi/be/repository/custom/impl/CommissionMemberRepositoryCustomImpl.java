package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionMemberFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionMemberRepositoryCustom;
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
public class CommissionMemberRepositoryCustomImpl  extends BaseRepositoryCustomImpl implements CommissionMemberRepositoryCustom {

    @Override
    public List<CommissionMemberEntity> searchRecords(CommissionMemberFilterDTO filter) {
        TypedQuery<CommissionMemberEntity> query = createQuery(filter, false);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public int getRecordsCount(CommissionMemberFilterDTO filter) {
        TypedQuery<Number> query = createQuery(filter, true);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    protected <T> TypedQuery<T> createQuery(CommissionMemberFilterDTO filter, boolean isCount) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(r) " : " r ");
        queryBuilder.append(" FROM ").append(CommissionMemberEntity.class.getSimpleName()).append(" r");
        queryBuilder.append(" WHERE 1=1 ");

        Integer id = filter.getId();
        if (Objects.nonNull(id)) {
            queryBuilder.append(" AND r.id = :id ");
            queryParameters.put("id", id);
        }


        String fullName = filter.getFullName();
        if (StringUtils.hasText(fullName)) {
            queryBuilder.append(" AND LOWER(CONCAT(r.firstName,coalesce(r.secondName,''),r.lastName)) like LOWER(:fullName) ");
            queryParameters.put("fullName", "%" + fullName.replace(" ","") + "%");
        }

        String firstName = filter.getFirstName();
        if (StringUtils.hasText(firstName)) {
            queryBuilder.append(" AND LOWER(r.firstName) like LOWER(:firstName) ");
            queryParameters.put("firstName", "%" + firstName + "%");
        }

        String lastName = filter.getLastName();
        if (StringUtils.hasText(lastName)) {
            queryBuilder.append(" AND LOWER(r.lastName) like LOWER(:lastName) ");
            queryParameters.put("lastName", "%" + lastName + "%");
        }

        List<Integer> excludedMembers = filter.getExcludedMembers();

        if (!CollectionUtils.isEmpty(excludedMembers)) {
            queryBuilder.append(" AND r.id not in (:excludedMembers) ");
            queryParameters.put("excludedMembers", excludedMembers);
        }

        Integer profGroup = filter.getProfGroup();
        if (Objects.nonNull(profGroup)) {
            queryBuilder.append(" AND r.profGroup.id = :profGroup ");
            queryParameters.put("profGroup", profGroup);
        }

        String commissionPosition = filter.getCommissionPosition();
        if (StringUtils.hasText(commissionPosition)) {
            queryBuilder.append(" AND r.commissionPosition.pk.id = :commissionPosition ");
            queryParameters.put("commissionPosition", commissionPosition);
        }

        Boolean isActive = filter.getIsActive();
        if (Objects.nonNull(isActive)) {
            queryBuilder.append(" AND r.active = :active ");
            queryParameters.put("active", isActive ? 1 : 0);
        }

        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }

            String sortFields = NomenclatureSortFields.sorterColumnMap().get(sortColumn);
            if (StringUtils.hasText(sortFields)) {
                String[] columns = sortFields.split(",");
                String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        Class<? extends Serializable> queryClass = isCount ? Number.class : CommissionMemberEntity.class;
        TypedQuery typedQuery = em.createQuery(queryBuilder.toString(), queryClass);
        queryParameters.keySet().forEach(key -> typedQuery.setParameter(key, queryParameters.get(key)));
        return typedQuery;
    }
}
