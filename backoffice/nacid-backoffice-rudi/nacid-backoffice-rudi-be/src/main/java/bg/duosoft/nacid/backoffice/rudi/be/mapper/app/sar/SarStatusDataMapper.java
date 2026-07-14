package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.status.SarStatusDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperConfig;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = StatusDataMapperConfig.class, uses = {
        IntegerToBooleanMapper.class,
})
public abstract class SarStatusDataMapper extends StatusDataMapperBase<SarStatusDataDTO> {
    @InheritConfiguration(name = "toStatusDataSectionBase")
    public abstract SarStatusDataDTO toStatusDataSection(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toStatusDataSectionBase")
    public abstract void overrideApplicationData(SarStatusDataDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(SarStatusDataDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);
    }

    @AfterMapping
    public void afterToStatusDataSection(RudiApplicationDTO source, @MappingTarget SarStatusDataDTO target) {
        super.afterToStatusDataSection(source, target);
    }
}
