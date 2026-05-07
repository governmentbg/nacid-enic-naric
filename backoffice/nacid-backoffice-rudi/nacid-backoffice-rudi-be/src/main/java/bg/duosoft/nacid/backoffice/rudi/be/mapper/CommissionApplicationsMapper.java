package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUserDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiCommissionApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityGroup;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiCommissionApplicationsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiCommissionApplicationsEntityPK;
import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class CommissionApplicationsMapper extends BaseObjectMapper<VRudiCommissionApplicationsEntity, RudiCommissionApplicationsDTO> {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @AfterMapping
    protected void afterToDTO(VRudiCommissionApplicationsEntity source, @MappingTarget RudiCommissionApplicationsDTO target) {
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

        target.setId(source.getPk().getId());
        target.setCalendarId(source.getPk().getCalendarId());
    }

    @AfterMapping
    protected void afterToEntity(RudiCommissionApplicationsDTO source, @MappingTarget VRudiCommissionApplicationsEntity target) {
        VRudiCommissionApplicationsEntityPK pk = new VRudiCommissionApplicationsEntityPK(source.getId(),source.getCalendarId());
        target.setPk(pk);
    }
}
