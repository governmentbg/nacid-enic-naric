package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.ERoleEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.RoleFilter;

import java.util.List;

public interface KeycloakRoleRepositoryCustom {

    List<ERoleEntity> selectRoles(RoleFilter filter, String realm, boolean getFORoles, boolean getBORoles);

    int selectRolesCount(RoleFilter filter, String realm, boolean getFORoles, boolean getBORoles);

}
