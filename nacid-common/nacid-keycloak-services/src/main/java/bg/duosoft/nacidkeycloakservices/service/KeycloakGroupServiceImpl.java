package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.access.GroupDTO;
import bg.duosoft.nacidfrontofficedto.user.access.filter.GroupFilterDTO;
import bg.duosoft.nacidkeycloakservices.exception.KeycloakServiceException;
import bg.duosoft.nacidkeycloakservices.mapper.GroupFilterMapper;
import bg.duosoft.nacidkeycloakservices.mapper.GroupMapper;
import bg.duosoft.nacidkeycloakservices.model.entity.EGroupEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.GroupFilter;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakGroupRepository;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakGroupRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakGroupServiceImpl extends BaseKeycloakService implements KeycloakGroupService {

    private final KeycloakGroupRepository keycloakGroupRepository;
    private final KeycloakGroupRepositoryCustom keycloakGroupRepositoryCustom;

    private final GroupMapper groupMapper;
    private final GroupFilterMapper groupFilterMapper;

    @Override
    public GroupDTO getGroupById(String groupId) {
        EGroupEntity groupEntity = keycloakGroupRepository.findById(groupId).orElse(null);
        if (groupEntity != null) {
            return groupMapper.toDto(groupEntity);
        }
        return null;
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        List<EGroupEntity> groupEntityList = keycloakGroupRepository.selectAllGroups(realm());
        return convertListResult(groupEntityList);
    }

    @Override
    public List<GroupDTO> getGroups(GroupFilterDTO filter) {
        GroupFilter groupFilter = groupFilterMapper.toFilter(filter);
        List<EGroupEntity> groupEntityList = keycloakGroupRepositoryCustom.selectGroups(groupFilter, realm());
        return convertListResult(groupEntityList);
    }

    @Override
    public int getGroupsCount(GroupFilterDTO filter) {
        GroupFilter groupFilter = groupFilterMapper.toFilter(filter);
        return keycloakGroupRepositoryCustom.selectGroupsCount(groupFilter, realm());
    }

    @Override
    public List<GroupDTO> getGroupsByRoleId(String roleId) {
        List<EGroupEntity> groupEntityList = keycloakGroupRepository.selectGroupsByRoleId(realm(), roleId);
        return convertListResult(groupEntityList);
    }

    @Override
    public List<GroupDTO> getGroupsByUserId(String userId) {
        List<EGroupEntity> groupEntityList = keycloakGroupRepository.selectGroupsByUserId(realm(), userId);
        return convertListResult(groupEntityList);
    }

    @Override
    public void createGroup(GroupDTO group) {
        GroupRepresentation representation = groupMapper.fromDto(group);
        if (Objects.nonNull(group.getParentGroup()) && StringUtils.hasText(group.getParentGroup().getId())) {
            createSubGroupForParent(group, representation);
        } else {
            Response response = realmResource().groups().add(representation);
            Response.StatusType statusInfo = response.getStatusInfo();
            if (statusInfo.getFamily() != Response.Status.Family.SUCCESSFUL) {
                log.error("=====KEYCLOAK SERVICES===== Cannot create group {} !", group.getName());
                throw new KeycloakServiceException("Code: " + statusInfo.getStatusCode() + ", Reason: " + statusInfo.getReasonPhrase());
            }
        }
    }

    private void createSubGroupForParent(GroupDTO group, GroupRepresentation representation) {
        GroupRepresentation parentRepresentation = getGroupRepresentationById(group.getParentGroup().getId());
        if (parentRepresentation.getSubGroups().stream().noneMatch(x -> x.getId().equals(representation.getId()))) {
            try (Response parentResponse = realmResource().groups().group(group.getParentGroup().getId()).subGroup(representation)) {
                if (parentResponse.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
                    log.error("=====KEYCLOAK SERVICES===== Creating Subgroup failed");
                }
            }
        }
    }

    @Override
    public void updateGroup(GroupDTO group) {
        GroupRepresentation representation = groupMapper.fromDto(group);
        GroupRepresentation original = getGroupRepresentationById(representation.getId());
        if (original != null) {
            setUnusedFieldsToGroupRepresentation(original, representation);
        } else {
            log.error("=====KEYCLOAK SERVICES===== Can not update non existing group");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Can not update non existing group");
        }

        String id = representation.getId();
        if (StringUtils.hasText(id)) {
            GroupResource groupResource = realmResource().groups().group(id);
            if (Objects.nonNull(groupResource)) {
                groupResource.update(representation);
                if (Objects.nonNull(group.getParentGroup())) {
                    if (StringUtils.hasText(group.getParentGroup().getId())) {
                        createSubGroupForParent(group, representation);
                    } else {
                        try (Response response = realmResource().groups().add(representation)) {
                            if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
                                log.error("=====KEYCLOAK SERVICES===== Moving group to top failed");
                            }
                        }
                    }
                }
            }
        } else {
            log.error("=====KEYCLOAK SERVICES===== Cannot update group with no id");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Cannot update group with no id");
        }
    }

    @Override
    public void deleteGroup(String groupId) {
        if (StringUtils.hasText(groupId)) {
            GroupResource group = realmResource().groups().group(groupId);
            if (Objects.nonNull(group)) {
                group.remove();
            }
        }
    }

    @Override
    public void addGroupToUser(String groupId, String userId) {
        realmResource().users().get(userId).joinGroup(groupId);
    }

    @Override
    public void removeGroupFromUser(String groupId, String userId) {
        realmResource().users().get(userId).leaveGroup(groupId);
    }

    private void setUnusedFieldsToGroupRepresentation(GroupRepresentation original, GroupRepresentation updated) {
        updated.setAccess(original.getAccess());
        updated.setClientRoles(original.getClientRoles());
        updated.setRealmRoles(original.getRealmRoles());
        updated.setPath(original.getPath());
        updated.setSubGroups(original.getSubGroups());
    }

    private GroupRepresentation getGroupRepresentationById(String groupId) {
        return realmResource().groups().group(groupId).toRepresentation();
    }

    private List<GroupDTO> convertListResult(List<EGroupEntity> groupEntityList) {
        if (groupEntityList == null) {
            return new ArrayList<>();
        }
        return groupMapper.toDtoListFromEntities(groupEntityList);
    }

    @Override
    public List<GroupDTO> getGroupParentsHierarchy(String groupId) {
        GroupDTO group = getGroupById(groupId);
        ArrayList<GroupDTO> parents = new ArrayList<>();
        while (Objects.nonNull(group.getParentGroup())) {
            parents.add(group.getParentGroup());
            group = group.getParentGroup();
        }
        Collections.reverse(parents);
        return parents;
    }

    @Override
    public List<GroupDTO> getGroupSubgroups(String groupId) {
        GroupRepresentation representation = getGroupRepresentationById(groupId);
        return groupMapper.toDtoListFromRepresentations(representation.getSubGroups());
    }

    @Override
    public GroupDTO getGroupByName(String groupName) {
        if (!StringUtils.hasText(groupName)) {
            return null;
        }

        EGroupEntity entity = keycloakGroupRepository.selectGroupByName(groupName);
        return groupMapper.toDto(entity);
    }

    @Override
    public List<GroupDTO> getChildrenGroups(String groupId) {
        if (!StringUtils.hasText(groupId)) {
            return null;
        }

        List<EGroupEntity> entities = keycloakGroupRepository.selectChildGroups(groupId);
        return groupMapper.toDtoListFromEntities(entities);
    }
}
