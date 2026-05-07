package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidkeycloakservices.model.filter.GroupFilter;
import bg.duosoft.nacidkeycloakservices.util.sort.GroupSorterUtils;
import bg.duosoft.nacidkeycloakservices.model.entity.EGroupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KeycloakGroupRepositoryCustomImpl extends BaseRepositoryCustomImpl implements KeycloakGroupRepositoryCustom {

    @Override
    public List<EGroupEntity> selectGroups(GroupFilter filter, String realm) {
        String buildQuery = buildQuery(filter, false, realm);
        Query query = em.createNativeQuery(buildQuery, EGroupEntity.class);
        addQueryParams(filter, query, realm);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult(filter.getPage() * filter.getPageSize());
        return fillResult(query);
    }

    @Override
    public int selectGroupsCount(GroupFilter filter, String realm) {
        String buildQuery = buildQuery(filter, true, realm);
        Query query = em.createNativeQuery(buildQuery);
        addQueryParams(filter, query, realm);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    private String buildQuery(GroupFilter filter, boolean isCount, String realm) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(*) " : " * ");
        queryBuilder.append(" FROM  public.keycloak_group g");
        queryBuilder.append(" WHERE 1=1 ");

        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND LOWER(g.name) like LOWER(:name) ");
        }
        if (StringUtils.hasText(filter.getExcludedUserId())) {
            queryBuilder.append(" AND id not in (select group_id from public.user_group_membership where user_id = :excludedUserId) ");
        }
        if (StringUtils.hasText(filter.getExcludedGroupId())) {
            queryBuilder.append(" AND id <>  :excludedGroupId");
        }

        if (StringUtils.hasText(realm)) {
            queryBuilder.append(" AND g.realm_id = :realm ");
        }

        if (!isCount) {
            String sortColumn = filter.getSortColumn();
            String sortOrder = filter.getSortOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }
            String[] columns = GroupSorterUtils.getQuerySortField(sortColumn).split(",");
            String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
            queryBuilder.append(" ORDER BY ").append(order);
        }
        return queryBuilder.toString();
    }

    private void addQueryParams(GroupFilter filter, Query query, String realm) {
        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            query.setParameter("name", "%" + name + "%");
        }

        if (StringUtils.hasText(filter.getExcludedUserId())) {
            query.setParameter("excludedUserId", filter.getExcludedUserId());
        }

        if (StringUtils.hasText(filter.getExcludedGroupId())) {
            query.setParameter("excludedGroupId", filter.getExcludedGroupId());
        }

        if (StringUtils.hasText(realm)) {
            query.setParameter("realm", realm);
        }
    }

    private List<EGroupEntity> fillResult(Query query) {
        List<EGroupEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return resultList;
    }

}
