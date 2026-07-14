package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidfrontofficedto.user.access.RoleDTO;
import bg.duosoft.nacidfrontofficedto.user.access.filter.RoleFilterDTO;
import bg.duosoft.nacidkeycloakservices.exception.KeycloakServiceException;
import bg.duosoft.nacidkeycloakservices.mapper.RoleFilterMapper;
import bg.duosoft.nacidkeycloakservices.mapper.RoleMapper;
import bg.duosoft.nacidkeycloakservices.model.entity.ERoleEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.RoleFilter;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakRoleRepository;
import bg.duosoft.nacidkeycloakservices.repository.KeycloakRoleRepositoryCustom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakRoleServiceImpl extends BaseKeycloakService implements KeycloakRoleService {

    private final KeycloakRoleRepository keycloakRoleRepository;
    private final KeycloakRoleRepositoryCustom keycloakRoleRepositoryCustom;

    private final RoleMapper roleMapper;
    private final RoleFilterMapper roleFilterMapper;
    
    @Override
    public RoleDTO getRoleById(String roleId) {
        ERoleEntity roleEntity = keycloakRoleRepository.getById(roleId);
        if(roleEntity != null){
            return roleMapper.toDto(roleEntity);
        }
        return null;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<ERoleEntity> roleEntityList = keycloakRoleRepository.selectAllRoles(realm());
        return convertListResult(roleEntityList);
    }

    @Override
    public List<RoleDTO> getRoles(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        List<ERoleEntity> roleEntityList = keycloakRoleRepositoryCustom.selectRoles(roleFilter, realm(), true, true);
        return convertListResult(roleEntityList);
    }

    @Override
    public int getRolesCount(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        return keycloakRoleRepositoryCustom.selectRolesCount(roleFilter, realm(), true, true);
    }

    @Override
    public List<RoleDTO> getFORoles(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        List<ERoleEntity> roleEntityList = keycloakRoleRepositoryCustom.selectRoles(roleFilter, realm(), true, false);
        return convertListResult(roleEntityList);
    }

    @Override
    public int getFORolesCount(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        return keycloakRoleRepositoryCustom.selectRolesCount(roleFilter, realm(), true, false);
    }

    @Override
    public List<RoleDTO> getBORoles(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        List<ERoleEntity> roleEntityList = keycloakRoleRepositoryCustom.selectRoles(roleFilter, realm(), false, true);
        return convertListResult(roleEntityList);
    }

    @Override
    public int getBORolesCount(RoleFilterDTO filter) {
        RoleFilter roleFilter = roleFilterMapper.toFilter(filter);
        return keycloakRoleRepositoryCustom.selectRolesCount(roleFilter, realm(), false, true);
    }

    @Override
    public List<RoleDTO> getRolesForGroupId(String groupId) {
        List<ERoleEntity> roleEntityList = keycloakRoleRepository.selectRolesFromGroup(realm(), groupId);
        return convertListResult(roleEntityList);
    }

    @Override
    public List<RoleDTO> getRolesForUserId(String userId) {
        List<ERoleEntity> roleEntityList = keycloakRoleRepository.selectRolesByUserId(realm(), userId);
        return convertListResult(roleEntityList);
    }

    @Override
    public void addRoleToUser(String roleId, String userId) {
        RoleRepresentation role = getRoleRepresentationById(roleId);
        roleRepresentationNullCheck(role, roleId);
        realmResource().users().get(userId).roles().realmLevel().add(Collections.singletonList(role));
    }

    @Override
    public void removeRoleFormUser(String roleId, String userId) {
        RoleRepresentation role = getRoleRepresentationById(roleId);
        roleRepresentationNullCheck(role, roleId);
        realmResource().users().get(userId).roles().realmLevel().remove(Collections.singletonList(role));
    }

    @Override
    public void addRoleToGroup(String roleId, String groupId) {
        RoleRepresentation role = getRoleRepresentationById(roleId);
        roleRepresentationNullCheck(role, roleId);
        realmResource().groups().group(groupId).roles().realmLevel().add(Collections.singletonList(role));
    }

    @Override
    public void removeRoleFromGroup(String roleId, String groupId) {
        RoleRepresentation role = getRoleRepresentationById(roleId);
        roleRepresentationNullCheck(role, roleId);
        realmResource().groups().group(groupId).roles().realmLevel().remove(Collections.singletonList(role));

    }

    private RoleRepresentation getRoleRepresentationById(String roleId){
        RoleRepresentation roleRepresentation = realmResource().rolesById().getRole(roleId);
        return roleRepresentation;
    }

    private List<RoleDTO> convertListResult(List<ERoleEntity> roleEntityList){
        if(roleEntityList == null){
            return new ArrayList<>();
        } else {
            return roleMapper.toDtoListFromEntities(roleEntityList);
        }
    }

    private void roleRepresentationNullCheck(RoleRepresentation role, String roleId){
        if(role == null) {
            log.warn("=====KEYCLOAK SERVICES===== Role with id {} does not exist", roleId);
            throw new KeycloakServiceException("=====KEYCLOAK SERVICES===== Role does not exist");
        }
    }
}
