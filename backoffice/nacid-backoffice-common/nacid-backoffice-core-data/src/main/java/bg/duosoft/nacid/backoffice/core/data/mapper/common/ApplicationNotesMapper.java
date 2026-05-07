package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationNotesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
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
public abstract class ApplicationNotesMapper extends BaseObjectMapper<ApplicationNotesEntity, ApplicationNotesDTO> {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @AfterMapping
    public void afterToDto(ApplicationNotesEntity source, @MappingTarget ApplicationNotesDTO target) {
        String user = target.getCreatedUser();
        if (StringUtils.hasText(user)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(SecurityGroup.BO_USERS);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO userDetails = usersMap.get(user);
                if (Objects.nonNull(userDetails)) {
                    target.setCreatedUserFullName(userDetails.getFullName());
                }
            }
        }
    }
}
