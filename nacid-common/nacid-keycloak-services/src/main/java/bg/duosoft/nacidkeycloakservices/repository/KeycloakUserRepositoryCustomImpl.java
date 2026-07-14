package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidkeycloakservices.model.filter.UserFilter;
import bg.duosoft.nacidkeycloakservices.properties.KeycloakServicesPropertyAccess;
import bg.duosoft.nacidkeycloakservices.util.sort.UserSorterUtils;
import bg.duosoft.nacidkeycloakservices.model.entity.EUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KeycloakUserRepositoryCustomImpl extends BaseRepositoryCustomImpl implements KeycloakUserRepositoryCustom {

    private final KeycloakServicesPropertyAccess keycloakServicesPropertyAccess;

    @Override
    public List<EUserEntity> selectUsers(UserFilter filter, String realm) {
        String buildQuery = buildQuery(filter, false, realm);
        Query query = em.createNativeQuery(buildQuery, EUserEntity.class);
        addQueryParams(filter, query, realm);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult(filter.getPage() * filter.getPageSize());
        return fillResult(query);
    }

    @Override
    public int selectUsersCount(UserFilter filter, String realm) {
        String buildQuery = buildQuery(filter, true, realm);
        Query query = em.createNativeQuery(buildQuery);
        addQueryParams(filter, query, realm);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    private String buildQuery(UserFilter filter, boolean isCount, String realm) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(isCount ? " COUNT(*) " : " * ");
        queryBuilder.append(" FROM public.user_entity u ");
        queryBuilder.append(" WHERE 1=1 ");
        queryBuilder.append(" AND u.service_account_client_link is null ");
        queryBuilder.append(" AND u.realm_id = :realmId ");


        if (StringUtils.hasText(filter.getExcludedGroupId())) {
            queryBuilder.append(" AND id not in (select user_id from public.user_group_membership where group_id = :excludedGroupId) ");
        }

        if (!CollectionUtils.isEmpty(filter.getIncludedRoles())) {
            queryBuilder.append(" and\n" +
                    "      ((id in (select distinct ugm.user_id\n" +
                    "              from public.user_group_membership ugm\n" +
                    "                       inner join public.group_role_mapping grm on ugm.group_id = grm.group_id\n" +
                    "                       inner join public.keycloak_role kr on kr.id = grm.role_id\n" +
                    "              where kr.name in (:roles)))\n" +
                    "   or (id in (select user_id\n" +
                    "              from public.user_role_mapping urm\n" +
                    "                       inner join public.keycloak_role kr on kr.id = urm.role_id\n" +
                    "              where kr.name in (:roles)))) ");
        }

        if (StringUtils.hasText(filter.getExcludedRoleId())) {
            queryBuilder.append(" and\n" +
                    "      ((id in (select distinct ugm.user_id\n" +
                    "              from public.user_group_membership ugm\n" +
                    "                       inner join public.group_role_mapping grm on ugm.group_id = grm.group_id\n" +
                    "              where grm.role_id not in (:excludedRoleId)))\n" +
                    "   or (id in (select user_id\n" +
                    "              from public.user_role_mapping urm\n" +
                    "              where urm.role_id  not in (:excludedRoleId)))) ");
        }

        if (!CollectionUtils.isEmpty(filter.getGroupNames())) {
            queryBuilder.append(" AND u.id IN (")
                    .append("SELECT ugm.user_id ")
                    .append("FROM public.user_group_membership ugm ")
                    .append("INNER JOIN public.keycloak_group g ON ugm.group_id = g.id ")
                    .append("WHERE g.name IN (:groupNames)")
                    .append(") ");
        }

        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND LOWER(CONCAT(u.first_name, ' ', u.last_name)) like LOWER(:name) ");
        }

        if (StringUtils.hasText(filter.getFirstName())) {
            queryBuilder.append(" AND LOWER(u.first_name) LIKE LOWER(:firstName) ");
        }

        if (StringUtils.hasText(filter.getLastName())) {
            queryBuilder.append(" AND LOWER(u.last_name) LIKE LOWER(:lastName) ");
        }

        if (StringUtils.hasText(filter.getUsername())) {
            queryBuilder.append(" AND LOWER(u.username) like LOWER(:username) ");
        }

        if (!CollectionUtils.isEmpty(filter.getExcludedUsernames())) {
            queryBuilder.append(" AND LOWER(u.username) NOT IN (:excludedUsernames) ");
        }

        if (StringUtils.hasText(filter.getFullName())) {
            queryBuilder.append(" AND (LOWER(CONCAT(u.first_name, ' ', u.last_name)) like LOWER(:fullName) or LOWER(u.username) like LOWER(:fullName)) ");
        }
        if (StringUtils.hasText(filter.getEmail())) {
            queryBuilder.append(" AND LOWER(u.email) like LOWER(:email) ");
        }

        if (Objects.nonNull(filter.getEnabled())) {
            queryBuilder.append(" AND u.enabled = :enabled ");
        }

        if (Objects.nonNull(filter.getEmailVerified())) {
            queryBuilder.append(" AND u.email_verified = :emailVerified ");
        }

        if (StringUtils.hasText(realm)) {
            queryBuilder.append(" AND u.realm_id = :realm ");
        }

        if (!isCount) {
            String sortColumn = filter.getSortColumn();
            String sortOrder = filter.getSortOrder();
            if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                sortOrder = Sortable.ASC_ORDER;
            }
            String[] columns = UserSorterUtils.getQuerySortField(sortColumn).split(",");
            String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
            queryBuilder.append(" ORDER BY ").append(order);
        }
        return queryBuilder.toString();
    }

    private void addQueryParams(UserFilter filter, Query query, String realm) {
        query.setParameter("realmId", keycloakServicesPropertyAccess.realm());

        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            query.setParameter("name", "%" + name + "%");
        }

        String firstName = filter.getFirstName();
        if (StringUtils.hasText(firstName)) {
            query.setParameter("firstName", "%" + firstName + "%");
        }

        String lastName = filter.getLastName();
        if (StringUtils.hasText(lastName)) {
            query.setParameter("lastName", "%" + lastName + "%");
        }

        String username = filter.getUsername();
        if (StringUtils.hasText(username)) {
            query.setParameter("username", "%" + username + "%");
        }

        List<String> excludedUsernames = filter.getExcludedUsernames();
        if (!CollectionUtils.isEmpty(excludedUsernames)) {
            query.setParameter(
                    "excludedUsernames",
                    excludedUsernames
                            .stream()
                            .map(String::toLowerCase)
                            .toList()
            );
        }

        if (!CollectionUtils.isEmpty(filter.getGroupNames())) {
            query.setParameter("groupNames", filter.getGroupNames());
        }

        String fullName = filter.getFullName();
        if (StringUtils.hasText(fullName)) {
            query.setParameter("fullName", "%" + fullName + "%");
        }

        String email = filter.getEmail();
        if (StringUtils.hasText(email)) {
            query.setParameter("email", "%" + email + "%");
        }

        if (StringUtils.hasText(filter.getExcludedGroupId())) {
            query.setParameter("excludedGroupId", filter.getExcludedGroupId());
        }

        if (StringUtils.hasText(filter.getExcludedRoleId())) {
            query.setParameter("excludedRoleId", filter.getExcludedRoleId());
        }

        if (!CollectionUtils.isEmpty(filter.getIncludedRoles())) {
            query.setParameter("roles", filter.getIncludedRoles());
        }

        Boolean enabled = filter.getEnabled();
        if (Objects.nonNull(enabled)) {
            query.setParameter("enabled", enabled);
        }

        if (Objects.nonNull(filter.getEmailVerified())) {
            query.setParameter("emailVerified", filter.getEmailVerified());
        }

        if (StringUtils.hasText(realm)) {
            query.setParameter("realm", realm);
        }
    }

    private List<EUserEntity> fillResult(Query query) {
        List<EUserEntity> resultList = query.getResultList();
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return resultList;
    }

}
