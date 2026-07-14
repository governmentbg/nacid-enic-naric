package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import bg.duosoft.nacidbackofficeshareddata.service.BaseStatusService;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class StatusDataMapperBase<D extends RudiStatusDataBaseDTO> {

    @Autowired
    private BaseStatusService baseStatusService;

    public abstract D toStatusDataSection(RudiApplicationDTO application);

    public abstract void overrideApplicationData(D source, @MappingTarget RudiApplicationDTO target);

    public void afterOverride(RudiStatusDataBaseDTO source, @MappingTarget RudiApplicationDTO target) {
        StatusDataMapperUtils.afterOverrideMandatoryStatusData(source, target);
    }

    public void afterToStatusDataSection(RudiApplicationDTO source, @MappingTarget RudiStatusDataBaseDTO target) {
        StatusDataMapperUtils.afterToMandatoryStatusDataDto(source, target);

        Integer applicationId = target.getApplicationId();
        target.setStatusHistory(baseStatusService.selectNormalStatusHistoryByApplicationId(applicationId, source.getApplication().getApplicationType().getId(), source.getApplication().getApplicationSubtype().getId()));
        target.setDocflowStatusHistory(baseStatusService.selectDocflowStatusHistoryByApplicationId(applicationId));
    }
}
