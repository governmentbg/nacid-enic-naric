package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationResponsibleUsersEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityGroup;
import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class ApplicationResponsibleUsersMapper extends BaseObjectMapper<ApplicationResponsibleUsersEntity, ApplicationResponsibleUsersDTO> {
    @Autowired
    private KeycloakUserService keycloakUserService;

    @AfterMapping
    public void afterToDto(ApplicationResponsibleUsersEntity source, @MappingTarget ApplicationResponsibleUsersDTO target) {
        String responsibleUser = target.getResponsibleUser();
        if (StringUtils.hasText(responsibleUser)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(SecurityGroup.BO_USERS);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO user = usersMap.get(responsibleUser);
                if (Objects.nonNull(user)) {
                    target.setFullName(user.getFullName().concat(" (").concat(user.getUsername()).concat(")"));
                }
            }
        }
    }

}
