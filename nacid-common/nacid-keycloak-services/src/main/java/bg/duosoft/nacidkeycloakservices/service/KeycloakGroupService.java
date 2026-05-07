package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.access.GroupDTO;
import bg.duosoft.nacidfrontofficedto.user.access.filter.GroupFilterDTO;

import java.util.List;

public interface KeycloakGroupService {

    GroupDTO getGroupById(String groupId);
    List<GroupDTO> getAllGroups();
    List<GroupDTO> getGroups(GroupFilterDTO filter);
    int getGroupsCount(GroupFilterDTO filter);
    List<GroupDTO> getGroupsByRoleId(String roleId);
    List<GroupDTO> getGroupsByUserId(String userId);

    void createGroup(GroupDTO group);
    void updateGroup(GroupDTO group);
    void deleteGroup(String groupId);

    void addGroupToUser(String groupId, String userId);
    void removeGroupFromUser(String groupId, String userId);
    List<GroupDTO> getGroupParentsHierarchy(String groupId);
    List<GroupDTO> getGroupSubgroups(String groupId);
    GroupDTO getGroupByName(String groupName);
    List<GroupDTO> getChildrenGroups(String groupId);

}
