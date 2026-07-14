package bg.duosoft.nacidkeycloakservices.repository;

import bg.duosoft.nacidkeycloakservices.model.entity.EUserEntity;
import bg.duosoft.nacidkeycloakservices.model.filter.UserFilter;

import java.util.List;

public interface KeycloakUserRepositoryCustom {

    List<EUserEntity> selectUsers(UserFilter filter, String realm);

    int selectUsersCount(UserFilter filter, String realm);

}
