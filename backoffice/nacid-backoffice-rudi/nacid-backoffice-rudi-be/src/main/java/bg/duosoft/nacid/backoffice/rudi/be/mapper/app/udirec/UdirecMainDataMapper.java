package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.main.UdirecMainDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperConfig;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", config = MainDataMapperConfig.class, uses = {
        IntegerToBooleanMapper.class,
})
public abstract class UdirecMainDataMapper extends MainDataMapperBase<UdirecMainDataDTO> {

    @InheritConfiguration(name = "toMainDataSectionBase")
    @Mapping(target = "personalDocumentTypeId", source = "application.personalDocumentType.id")
    public abstract UdirecMainDataDTO toMainDataSection(RudiApplicationDTO application);

    @AfterMapping
    public void afterToMainDataSection(RudiApplicationDTO source, @MappingTarget UdirecMainDataDTO target) {
        super.afterToMainDataSection(source, target);
        target.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(source.getApplication().getDocumentReceiveMethods()));
    }

    @InheritInverseConfiguration(name = "toMainDataSection")
    public abstract void overrideApplicationData(UdirecMainDataDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(UdirecMainDataDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);

        ApplicationDTO application = target.getApplication();
        if (Objects.nonNull(application)) {
            String personalDocumentTypeId = source.getPersonalDocumentTypeId();
            if (!StringUtils.hasText(personalDocumentTypeId)) {
                application.setPersonalDocumentType(null);
            }
            target.getApplication().setDocumentReceiveMethods(DocumentReceiveMethodUtils.convertToApplicationDocumentReceiveMethod(source.getDocumentReceiveMethod()));
        }
    }

}
