package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocflowStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocflowStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
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

@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class})
public abstract class ApplicationDocflowStatusHistoryMapper extends BaseObjectMapper<ApplicationDocflowStatusHistoryEntity, ApplicationDocflowStatusHistoryDTO> {
    @Autowired
    private KeycloakUserService keycloakUserService;

    @AfterMapping
    public void afterToDto(ApplicationDocflowStatusHistoryEntity source, @MappingTarget ApplicationDocflowStatusHistoryDTO target) {
        String userCreated = target.getUserCreated();
        if (StringUtils.hasText(userCreated)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(SecurityGroup.BO_USERS);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO userDetails = usersMap.get(userCreated);
                if (Objects.nonNull(userDetails)) {
                    target.setUserCreated(userDetails.getFullName());
                }
            }
        }
    }
}
