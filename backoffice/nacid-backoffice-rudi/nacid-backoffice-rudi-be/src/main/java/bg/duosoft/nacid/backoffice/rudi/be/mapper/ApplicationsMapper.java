package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityGroup;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
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
public abstract class ApplicationsMapper extends BaseObjectMapper<VRudiApplicationsEntity, RudiApplicationsDTO> {
    @Autowired
    private KeycloakUserService keycloakUserService;

    @AfterMapping
    protected void afterToDTO(VRudiApplicationsEntity source, @MappingTarget RudiApplicationsDTO target) {
        String responsibleUserName = source.getResponsibleUserName();
        if (StringUtils.hasText(responsibleUserName)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(SecurityGroup.BO_RUDI_VIEW_APPS);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO user = usersMap.get(responsibleUserName);
                if (Objects.nonNull(user)) {
                    target.setResponsibleUserData(new ApplicationResponsibleUserDataDTO(responsibleUserName, user.getFullName().concat(" (").concat(user.getUsername()).concat(")")));
                }

            }
        }

    }
}
