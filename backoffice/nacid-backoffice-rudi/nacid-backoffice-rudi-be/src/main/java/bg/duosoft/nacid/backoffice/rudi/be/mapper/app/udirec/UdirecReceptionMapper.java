package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.reception.UdirecReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperConfig;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", config = ReceptionMapperConfig.class)
public abstract class UdirecReceptionMapper extends ReceptionMapperBase<UdirecReceptionDTO> {

    @InheritConfiguration(name = "toReceptionBase")
    @Mapping(target = "trainingCourseSpecialities", source = "trainingCourse.trainingCourseSpecialities")
    @Mapping(target = "personalDocumentTypeId", source = "application.personalDocumentType.id")
    public abstract UdirecReceptionDTO toReceptionDto(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toReceptionDto")
    public abstract void overrideApplicationData(UdirecReceptionDTO source, @MappingTarget RudiApplicationDTO target);

    @BeforeMapping
    public void beforeOverride(UdirecReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
        if (Objects.isNull(target.getApplication())) {
            target.setApplication(new ApplicationDTO());
        }

        ApplicationDTO application = target.getApplication();
        application.setApplicationType(new ApplicationTypeDTO(ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appType()));
        application.setApplicationSubtype(new ApplicationSubtypeDTO(ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appSubType()));
    }

    @AfterMapping
    public void afterOverride(UdirecReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
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
    public void afterToReception(RudiApplicationDTO source, @MappingTarget UdirecReceptionDTO target) {
        super.afterToReception(source, target);
        target.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(source.getApplication().getDocumentReceiveMethods()));
    }

}
