package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.ERoleEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeycloakRoleRepository extends BaseRepository<ERoleEntity, String> {
    @Query(value = "SELECT * FROM public.keycloak_role e where e.realm_id = ?1 and e.client_role = false and e.id in (select x.role_id from public.group_role_mapping x where x.group_id = ?2)", nativeQuery = true)
    List<ERoleEntity> selectRolesFromGroup(String realm, String groupId);

    @Query(value = "SELECT * FROM public.keycloak_role e where e.realm_id = ?1 and e.client_role = false and e.id in (select x.role_id from public.user_role_mapping x where x.user_id = ?2)", nativeQuery = true)
    List<ERoleEntity> selectRolesByUserId(String realm, String userId);

    @Query(value = "SELECT * FROM public.keycloak_role e where e.realm_id = ?1 and e.client_role = false", nativeQuery = true)
    List<ERoleEntity> selectAllRoles(String realm);
}
