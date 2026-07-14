package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.EGroupEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeycloakGroupRepository extends BaseRepository<EGroupEntity, String> {
    @Query(value = "SELECT g FROM EGroupEntity g where g.realmId = ?1 and g.id in (select grrol.id.groupId from EGroupRoleMapping grrol where grrol.id.roleId = ?2)")
    List<EGroupEntity> selectGroupsByRoleId(String realm, String roleId);

    @Query(value = "SELECT g FROM EGroupEntity g where g.realmId = ?1 and g.id in (select mem.id.groupId from EUserGroupMembership mem where mem.id.userId = ?2)")
    List<EGroupEntity> selectGroupsByUserId(String realm, String userId);

    @Query(value = "SELECT e FROM EGroupEntity e where e.realmId = ?1")
    List<EGroupEntity> selectAllGroups(String realm);

    @Query(value = "SELECT e FROM EGroupEntity e where e.name = ?1")
    EGroupEntity selectGroupByName(String name);

    @Query(value = "SELECT e FROM EGroupEntity e where e.parentGroup.id = ?1")
    List<EGroupEntity> selectChildGroups(String id);
}
