package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.reception.DocrecReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperConfig;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", config = ReceptionMapperConfig.class)
public abstract class DocrecReceptionMapper extends ReceptionMapperBase<DocrecReceptionDTO> {

    @InheritConfiguration(name = "toReceptionBase")
    @Mapping(target = "personalDocumentTypeId", source = "application.personalDocumentType.id")
    public abstract DocrecReceptionDTO toReceptionDto(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toReceptionDto")
    public abstract void overrideApplicationData(DocrecReceptionDTO source, @MappingTarget RudiApplicationDTO target);

    @BeforeMapping
    public void beforeOverride(DocrecReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
        if (Objects.isNull(target.getApplication())) {
            target.setApplication(new ApplicationDTO());
        }

        ApplicationDTO application = target.getApplication();
        application.setApplicationType(new ApplicationTypeDTO(ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION.appType()));
        application.setApplicationSubtype(new ApplicationSubtypeDTO(ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION.appSubType()));
    }

    @AfterMapping
    public void afterOverride(DocrecReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
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

    @AfterMapping
    public void afterToReception(RudiApplicationDTO source, @MappingTarget DocrecReceptionDTO target) {
        super.afterToReception(source, target);
        target.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(source.getApplication().getDocumentReceiveMethods()));
    }

}
