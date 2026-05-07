package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;

import bg.duosoft.nacidbackofficeshareddata.utils.ResponsibleUserChangeUtils;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Objects;

public abstract class MainDataMapperBase<D extends RudiMainDataBaseDTO> {

    @Autowired
    private KeycloakUserService keycloakUserService;

    public abstract D toMainDataSection(RudiApplicationDTO application);

    public abstract void overrideApplicationData(D source, @MappingTarget RudiApplicationDTO target);

    public void afterOverride(RudiMainDataBaseDTO source, @MappingTarget RudiApplicationDTO target) {
        MainDataMapperUtils.afterOverrideMandatoryMainData(source, target);
        ResponsibleUserChangeUtils.processResponsibleUserChange(target.getApplication(), source.getResponsibleUser());
    }

    public void afterToMainDataSection(RudiApplicationDTO source, @MappingTarget RudiMainDataBaseDTO target) {
        MainDataMapperUtils.afterToMandatoryMainDataDto(source, target);
        String activeResponsibleUser = ResponsibleUserChangeUtils.getActiveResponsibleUser(source.getApplication());
        if (StringUtils.hasText(activeResponsibleUser)) {
            target.setResponsibleUser(activeResponsibleUser);
        }

        String userCreated = source.getApplication().getUserCreated();
        NacidUserDetailsDTO userCreatedDetails = keycloakUserService.getUserByUsername(userCreated);
        if (Objects.nonNull(userCreatedDetails)) {
            target.setUserCreatedFullName(userCreatedDetails.getFullName());
        }
    }

}
