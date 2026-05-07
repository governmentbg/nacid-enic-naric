package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidkeycloakservices.model.filter.RoleFilter;
import bg.duosoft.nacidkeycloakservices.util.sort.RoleSorterUtils;
import bg.duosoft.nacidkeycloakservices.model.entity.ERoleEntity;
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
public class KeycloakRoleRepositoryCustomImpl extends BaseRepositoryCustomImpl implements KeycloakRoleRepositoryCustom {


    @Override
    public List<ERoleEntity> selectRoles(RoleFilter filter, String realm, boolean getFORoles, boolean getBORoles) {
        String buildQuery = buildQuery(filter, false, realm, getFORoles, getBORoles);
        Query query = em.createNativeQuery(buildQuery, ERoleEntity.class);
        addQueryParams(filter, query, realm);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult(filter.getPage() * filter.getPageSize());
        return fillResult(query);
    }

    @Override
    public int selectRolesCount(RoleFilter filter, String realm, boolean getFORoles, boolean getBORoles) {
        String buildQuery = buildQuery(filter, true, realm, getFORoles, getBORoles);
        Query query = em.createNativeQuery(buildQuery);
        addQueryParams(filter, query, realm);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    private String buildQuery(RoleFilter filter, boolean isCount, String realm, boolean getFORoles, boolean getBORoles) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(*) " : " * ");
        queryBuilder.append(" FROM public.keycloak_role r ");
        queryBuilder.append(" WHERE r.client_role = false ");

        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND LOWER(r.name) like LOWER(:name) ");
        }

        if (StringUtils.hasText(filter.getExcludedUserId())) {
            queryBuilder.append(" AND id not in (select role_id from public.user_role_mapping where user_id = :excludedUserId) ");
        }

        if (StringUtils.hasText(filter.getExcludedGroupId())) {
            queryBuilder.append(" AND id not in (select role_id from public.group_role_mapping where group_id = :excludedGroupId) ");
        }

        if (StringUtils.hasText(filter.getDescription())) {
            queryBuilder.append(" AND LOWER(r.description) like LOWER(:description) ");
        }

        if (StringUtils.hasText(realm)) {
            queryBuilder.append(" AND r.realm_id = :realm ");
        }

        if (getFORoles && !getBORoles) {
            queryBuilder.append(" AND r.name like 'FO_%' ");
        }

        if (!getFORoles && getBORoles) {
            queryBuilder.append(" AND r.name like 'BO_%' ");
        }

        if (!isCount) {
            String sortColumn = filter.getSortColumn();
            String sortOrder = filter.getSortOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }
            String[] columns = RoleSorterUtils.getQuerySortField(sortColumn).split(",");
            String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
            queryBuilder.append(" ORDER BY ").append(order);
        }
        return queryBuilder.toString();
    }

    private void addQueryParams(RoleFilter filter, Query query, String realm) {
        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            query.setParameter("name", "%" + name + "%");
        }

        String description = filter.getDescription();
        if (StringUtils.hasText(description)) {
            query.setParameter("description", "%" + description + "%");
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

    private List<ERoleEntity> fillResult(Query query) {
        List<ERoleEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return resultList;
    }

}
