package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.status.DocrecStatusDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperConfig;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperUtils;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;


@Mapper(componentModel = "spring", config = StatusDataMapperConfig.class, uses = {
        IntegerToBooleanMapper.class,
})
public abstract class DocrecStatusDataMapper extends StatusDataMapperBase<DocrecStatusDataDTO> {
    @InheritConfiguration(name = "toStatusDataSectionBase")
    @Mapping(target = "recognizedEduLevel.id", source = "applicationRecognizedDetails.recognizedEduLevel")
    public abstract DocrecStatusDataDTO toStatusDataSection(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toStatusDataSection")
    public abstract void overrideApplicationData(DocrecStatusDataDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(DocrecStatusDataDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);
        StatusDataMapperUtils.afterOverrideUdirecDocrecStatusData(source, target);
    }

    @AfterMapping
    public void afterToStatusDataSection(RudiApplicationDTO source, @MappingTarget DocrecStatusDataDTO target) {
        super.afterToStatusDataSection(source, target);
        StatusDataMapperUtils.afterToUdirecDocrecStatusDataSection(source, target);
    }
}
