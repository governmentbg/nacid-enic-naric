package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.filter.UserFilterDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface KeycloakUserService {

    NacidUserDetailsDTO getUserById(String userId);
    NacidUserDetailsDTO getUserByUsername(String username);
    List<NacidUserDetailsDTO> getUsersByUsernames(List<String> usernames);
    NacidUserDetailsDTO getUserByEmail(String email);

    boolean userExistsForUsername(String username);
    boolean userExistsForEmail(String email);

    List<BaseUserDetailsDTO> getAllUsers();
    List<BaseUserDetailsDTO> getUsers(UserFilterDTO filter);
    int getUsersCount(UserFilterDTO filter);
    List<BaseUserDetailsDTO> getUsersFromGroup(String groupId);
    List<BaseUserDetailsDTO> getUsersFromRole(String roleId);
    Map<String, BaseUserDetailsDTO> getUsersFromRoleNameCached(String roleName);
    void registerUser(NacidUserDetailsDTO registration);
    void updateUser(NacidUserDetailsDTO registration);
    void updateUserPassword(String userId, String newPassword);

    List<String> selectEmailsOfGroupMembers(List<String> groupNames);
    Set<NacidUserDetailsDTO> getUsersFromGroupHierarchy(String groupName);
    Map<String, NacidUserDetailsDTO> getUsersMapFromGroupHierarchyCached(String groupName);

}
