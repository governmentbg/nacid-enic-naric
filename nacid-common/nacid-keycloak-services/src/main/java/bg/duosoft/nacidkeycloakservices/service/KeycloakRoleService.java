package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.access.RoleDTO;
import bg.duosoft.nacidfrontofficedto.user.access.filter.RoleFilterDTO;

import java.util.List;

public interface KeycloakRoleService {

    RoleDTO getRoleById(String roleId);
    List<RoleDTO> getAllRoles();
    List<RoleDTO> getRoles(RoleFilterDTO filter);
    int getRolesCount(RoleFilterDTO filter);
    List<RoleDTO> getFORoles(RoleFilterDTO filter);
    int getFORolesCount(RoleFilterDTO filter);
    List<RoleDTO> getBORoles(RoleFilterDTO filter);
    int getBORolesCount(RoleFilterDTO filter);
    List<RoleDTO> getRolesForGroupId(String groupId);
    List<RoleDTO> getRolesForUserId(String userId);

    void addRoleToUser(String roleId, String userId);
    void removeRoleFormUser(String roleId, String userId);

    void addRoleToGroup(String roleId, String groupId);
    void removeRoleFromGroup(String roleId, String groupId);
}
