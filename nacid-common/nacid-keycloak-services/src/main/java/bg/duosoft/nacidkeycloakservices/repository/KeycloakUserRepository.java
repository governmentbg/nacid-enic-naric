package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.EUserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeycloakUserRepository extends BaseRepository<EUserEntity, String> {

    @Query(value = "SELECT * FROM public.user_entity e where e.realm_id = ?2 and lower(e.username) = lower( ?1 ) ", nativeQuery = true)
    EUserEntity selectByUsername(String username, String realm);

    @Query(value = "SELECT * FROM public.user_entity e WHERE e.realm_id = :realm AND e.username IN (:usernames)", nativeQuery = true)
    List<EUserEntity> selectByUsernames(@Param("usernames") List<String> usernames, @Param("realm") String realm);

    @Query(value = "SELECT * FROM public.user_entity e where e.realm_id = ?2 and lower(e.email) = lower( ?1 ) ", nativeQuery = true)
    List<EUserEntity> selectByEmail(String email, String realm);

    @Query(value = "SELECT * FROM public.user_entity e where e.realm_id = ?1", nativeQuery = true)
    List<EUserEntity> selectAll(String realm);

    @Query(value = "SELECT * FROM public.user_entity e where e.realm_id = ?1 and e.id in (select x.user_id from public.user_group_membership x where x.group_id = ?2)", nativeQuery = true)
    List<EUserEntity> selectUsersFromGroup(String realm, String groupId);

    @Query(value = "SELECT * FROM public.user_entity e where e.realm_id = ?1 and e.id in (select x.user_id from public.user_role_mapping x where x.role_id = ?2)", nativeQuery = true)
    List<EUserEntity> selectUsersFromRole(String realm, String roleId);

    @Query(value = "SELECT distinct e.email\n" +
            "FROM public.user_entity e\n" +
            "where e.realm_id = ?1\n" +
            "  and e.email is not null\n" +
            "  and e.email <> ''\n" +
            "  and e.id in (select x.user_id\n" +
            "               from public.user_group_membership x\n" +
            "                        join public.keycloak_group kg on x.group_id = kg.id\n" +
            "               where kg.name in ?2);", nativeQuery = true)
    List<String> selectEmailsOfGroupMembers(String realm, List<String> groupNames);

    @Query(value = "SELECT *\n" +
            "FROM public.user_entity u\n" +
            "where u.realm_id = ?1\n" +
            "  AND u.service_account_client_link is null\n" +
            "  and ((id in (select distinct ugm.user_id\n" +
            "               from public.user_group_membership ugm\n" +
            "                        inner join public.group_role_mapping grm on ugm.group_id = grm.group_id\n" +
            "                        inner join public.keycloak_role kr on kr.id = grm.role_id\n" +
            "               where kr.name =?2))\n" +
            "    or (id in (select user_id\n" +
            "               from public.user_role_mapping urm\n" +
            "                        inner join public.keycloak_role kr on kr.id = urm.role_id\n" +
            "               where kr.name = ?2)))", nativeQuery = true)
    List<EUserEntity> selectUsersFromRoleName(String realm, String roleName);

}
