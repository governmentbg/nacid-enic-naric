package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.access.GroupDTO;
import bg.duosoft.nacidfrontofficedto.user.filter.UserFilterDTO;
import bg.duosoft.nacidkeycloakservices.exception.KeycloakServiceException;
import bg.duosoft.nacidkeycloakservices.mapper.BaseUserDetailsMapper;
import bg.duosoft.nacidkeycloakservices.mapper.UserDetailsMapper;
import bg.duosoft.nacidkeycloakservices.mapper.UserFilterMapper;
import bg.duosoft.nacidkeycloakservices.model.entity.EUserEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.UserFilter;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakUserRepository;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakUserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakUserServiceImpl extends BaseKeycloakService implements KeycloakUserService {

    private final KeycloakUserRepository keycloakUserRepository;
    private final KeycloakUserRepositoryCustom keycloakUserRepositoryCustom;

    private final UserDetailsMapper userDetailsMapper;
    private final BaseUserDetailsMapper baseUserDetailsMapper;
    private final UserFilterMapper userFilterMapper;
    private final KeycloakGroupService keycloakGroupService;

    @Override
    public NacidUserDetailsDTO getUserById(String userId) {
        UserRepresentation representation = getRepresentationForUserId(userId);
        if (representation != null) {
            return userDetailsMapper.toDto(representation);
        }
        return null;
    }

    @Override
    public NacidUserDetailsDTO getUserByUsername(String username) {
        EUserEntity userEntity = keycloakUserRepository.selectByUsername(username, realm());
        if (userEntity != null) {
            return getUserById(userEntity.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<NacidUserDetailsDTO> getUsersByUsernames(List<String> usernames) {
        List<EUserEntity> entities =
                keycloakUserRepository.selectByUsernames(usernames.stream().map(String::toLowerCase).toList(), realm());

        return entities.stream()
                .map(e -> getUserById(e.getId()))
                .toList();
    }

    @Override
    public NacidUserDetailsDTO getUserByEmail(String email) {
        List<EUserEntity> userEntityList = keycloakUserRepository.selectByEmail(email, realm());
        if (userEntityList == null || userEntityList.size() == 0) {
            return null;
        } else if (userEntityList.size() == 1) {
            return getUserById(userEntityList.get(0).getId());
        } else {
            log.error("=====KEYCLOAK SERVICES===== Too many users for email {}", email);
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Too many users for email");
        }
    }

    @Override
    public boolean userExistsForUsername(String username) {
        EUserEntity userEntity = keycloakUserRepository.selectByUsername(username, realm());
        if (userEntity != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userExistsForEmail(String email) {
        List<EUserEntity> userEntityList = keycloakUserRepository.selectByEmail(email, realm());
        return userEntityList != null && userEntityList.size() > 0;
    }

    @Override
    public List<BaseUserDetailsDTO> getAllUsers() {
        List<EUserEntity> userEntityList = keycloakUserRepository.selectAll(realm());
        return convertListResult(userEntityList);
    }

    @Override
    public List<BaseUserDetailsDTO> getUsers(UserFilterDTO filter) {
        UserFilter userFilter = userFilterMapper.toFilter(filter);
        List<EUserEntity> userEntityList = keycloakUserRepositoryCustom.selectUsers(userFilter, realm());
        return convertListResult(userEntityList);
    }

    @Override
    public int getUsersCount(UserFilterDTO filter) {
        UserFilter userFilter = userFilterMapper.toFilter(filter);
        return keycloakUserRepositoryCustom.selectUsersCount(userFilter, realm());
    }

    @Override
    public List<BaseUserDetailsDTO> getUsersFromGroup(String groupId) {
        List<EUserEntity> userEntityList = keycloakUserRepository.selectUsersFromGroup(realm(), groupId);
        return convertListResult(userEntityList);
    }

    @Override
    public List<BaseUserDetailsDTO> getUsersFromRole(String roleId) {
        List<EUserEntity> userEntityList = keycloakUserRepository.selectUsersFromRole(realm(), roleId);
        return convertListResult(userEntityList);
    }

    @Override
    @Cacheable(value = "KeycloakUsers", key = "'roleName-' + #roleName")
    public Map<String, BaseUserDetailsDTO> getUsersFromRoleNameCached(String roleName) {
        List<EUserEntity> users = keycloakUserRepository.selectUsersFromRoleName(realm(), roleName);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        return users.stream().collect(Collectors.toMap(EUserEntity::getUsername, baseUserDetailsMapper::toDto));
    }

    @Override
    public void registerUser(NacidUserDetailsDTO registration) {
        UserRepresentation user = userDetailsMapper.fromDto(registration);
        Response response = realmResource().users().create(user);
        Response.StatusType statusInfo = response.getStatusInfo();
        if (statusInfo.getStatusCode() != 201) {
            throw new KeycloakServiceException("Code: " + statusInfo.getStatusCode() + ", Reason: " + statusInfo.getReasonPhrase());
        }
    }

    @Override
    public void updateUser(NacidUserDetailsDTO registration) {
        UserRepresentation representation = userDetailsMapper.fromDto(registration);
        UserRepresentation original = getRepresentationForUserId(registration.getId());
        if (original != null) {
            setUnusedFieldsToUserRepresentation(original, representation);
        } else {
            log.error("=====KEYCLOAK SERVICES===== Can not update non existing user");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Can not update non existing user");
        }
        updateUserRepresentation(representation);
    }

    @Override
    public void updateUserPassword(String userId, String newPassword) {
        UserRepresentation representation = getRepresentationForUserId(userId);
        if (representation != null) {
            representation.setCredentials(Collections.singletonList(userDetailsMapper.mapPasswordToCredentialRepresentation(newPassword)));
        } else {
            log.error("=====KEYCLOAK SERVICES===== Can not update non existing user's password");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Can not update non existing user's password");
        }
        updateUserRepresentation(representation);
    }

    @Override
    public List<String> selectEmailsOfGroupMembers(List<String> groupNames) {
        if (CollectionUtils.isEmpty(groupNames)) {
            return null;
        }

        return keycloakUserRepository.selectEmailsOfGroupMembers(realm(), groupNames);
    }

    @Override
    public Set<NacidUserDetailsDTO> getUsersFromGroupHierarchy(String groupName) {
        if (!StringUtils.hasText(groupName)) {
            return null;
        }

        GroupDTO mainGroup = keycloakGroupService.getGroupByName(groupName);
        if (Objects.isNull(mainGroup)) {
            return null;
        }

        Set<NacidUserDetailsDTO> allUsers = new HashSet<>();

        List<GroupDTO> allGroupsFromHierarchy = selectGroups(mainGroup);
        for (GroupDTO groupDTO : allGroupsFromHierarchy) {
            List<UserRepresentation> members = realmResource().groups().group(groupDTO.getId()).members();
            List<NacidUserDetailsDTO> groupUsers = userDetailsMapper.toDtoList(members);
            if (!CollectionUtils.isEmpty(groupUsers)) {
                allUsers.addAll(groupUsers);
            }
        }

        return CollectionUtils.isEmpty(allUsers) ? null : allUsers;
    }

    @Override
    @Cacheable(value = "KeycloakUsers", key = "'groupName-' + #groupName")
    public Map<String, NacidUserDetailsDTO> getUsersMapFromGroupHierarchyCached(String groupName) {
        Set<NacidUserDetailsDTO> usersDetails = getUsersFromGroupHierarchy(groupName);
        if (!CollectionUtils.isEmpty(usersDetails)) {
            return usersDetails.stream().collect(Collectors.toMap(NacidUserDetailsDTO::getUsername, e -> e));
        }

        return null;
    }

    private List<GroupDTO> selectGroups(GroupDTO mainGroup) {
        List<GroupDTO> resultGroups = new ArrayList<>();
        if (Objects.isNull(mainGroup)) {
            return resultGroups;
        }
        resultGroups.add(mainGroup);

        List<GroupDTO> childrenGroups = keycloakGroupService.getChildrenGroups(mainGroup.getId());
        if (!CollectionUtils.isEmpty(childrenGroups)) {
            for (GroupDTO childrenGroup : childrenGroups) {
                List<GroupDTO> innerGroups = selectGroups(childrenGroup);
                if (!CollectionUtils.isEmpty(innerGroups)) {
                    resultGroups.addAll(innerGroups);
                }
            }
        }

        return resultGroups;
    }


    private void setUnusedFieldsToUserRepresentation(UserRepresentation original, UserRepresentation updated) {
        updated.setAccess(original.getAccess());
        updated.setClientConsents(original.getClientConsents());
        updated.setClientRoles(original.getClientRoles());
        updated.setDisableableCredentialTypes(original.getDisableableCredentialTypes());
        updated.setFederatedIdentities(original.getFederatedIdentities());
        updated.setGroups(original.getGroups());
        updated.setNotBefore(original.getNotBefore());
        updated.setRealmRoles(original.getRealmRoles());
        updated.setOrigin(original.getOrigin());
        updated.setFederationLink(original.getFederationLink());
        updated.setRequiredActions(original.getRequiredActions());
        updated.setSelf(original.getSelf());
        updated.setServiceAccountClientId(original.getServiceAccountClientId());
        updated.setSocialLinks(original.getSocialLinks());
    }

    private void updateUserRepresentation(UserRepresentation userRepresentation) {
        String id = userRepresentation.getId();
        if (StringUtils.hasText(id)) {
            UserResource userResource = realmResource().users().get(userRepresentation.getId());
            if (Objects.nonNull(userResource)) {
                userResource.update(userRepresentation);
            }
        } else {
            log.error("=====KEYCLOAK SERVICES===== Cannot update user with no id");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Cannot update user with no id");
        }
    }

    private UserRepresentation getRepresentationForUserId(String userId) {
        if (userId == null) {
            log.error("=====KEYCLOAK SERVICES===== Cannot select user for null id");
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Cannot select user for null id");
        }
        UserResource userResource = realmResource().users().get(userId);
        if (Objects.isNull(userResource)) {
            return null;
        }

        try {
            UserRepresentation representation = userResource.toRepresentation();
            return representation;
        } catch (Exception e) {
            log.error("=====KEYCLOAK SERVICES===== Cannot select user [{}]", userId);
            log.error(e.getMessage(), e);
            throw new KeycloakServiceException(e);
        }
    }

    private List<BaseUserDetailsDTO> convertListResult(List<EUserEntity> userEntityList) {
        if (userEntityList == null) {
            return new ArrayList<>();
        } else {
            return baseUserDetailsMapper.toDtoListFromEntities(userEntityList);
        }
    }

}
