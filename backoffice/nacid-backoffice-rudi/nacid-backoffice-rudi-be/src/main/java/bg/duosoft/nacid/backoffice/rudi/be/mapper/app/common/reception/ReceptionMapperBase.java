package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperUtils;
import org.mapstruct.MappingTarget;

public abstract class ReceptionMapperBase<D extends RudiBaseReceptionDTO> {

    public abstract D toReceptionDto(RudiApplicationDTO application);

    public abstract void overrideApplicationData(D source, @MappingTarget RudiApplicationDTO target);

    public void afterOverride(D source, @MappingTarget RudiApplicationDTO target) {
        MainDataMapperUtils.afterOverrideMandatoryMainData(source, target);
        EduDataMapperUtils.afterOverrideMandatoryEduData(source, target);
    }

    public void afterToReception(RudiApplicationDTO source, @MappingTarget D target) {
        MainDataMapperUtils.afterToMandatoryMainDataDto(source, target);
    }

}
