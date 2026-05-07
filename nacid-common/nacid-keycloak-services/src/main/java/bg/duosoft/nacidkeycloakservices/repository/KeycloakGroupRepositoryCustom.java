package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.EGroupEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.GroupFilter;

import java.util.List;

public interface KeycloakGroupRepositoryCustom {

    List<EGroupEntity> selectGroups(GroupFilter filter, String realm);

    int selectGroupsCount(GroupFilter filter, String realm);

}
