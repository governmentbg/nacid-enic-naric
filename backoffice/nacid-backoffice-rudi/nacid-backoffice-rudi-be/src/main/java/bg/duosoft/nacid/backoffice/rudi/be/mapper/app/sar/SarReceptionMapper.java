package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.reception.SarReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception.ReceptionMapperConfig;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", config = ReceptionMapperConfig.class, uses = {IntegerToBooleanMapper.class})
public abstract class SarReceptionMapper extends ReceptionMapperBase<SarReceptionDTO> {

    @InheritConfiguration(name = "toReceptionBase")
    @Mapping(target = "diplomaOwnerId", source = "trainingCourse.diplomaOwner.id")
    @Mapping(target = "trainingCourseSpecialities", source = "trainingCourse.trainingCourseSpecialities")
    @Mapping(target = "sarFlag.statuteFlag", source = "sarApplication.isStatute")
    @Mapping(target = "sarFlag.authenticityFlag", source = "sarApplication.isAuthenticity")
    @Mapping(target = "sarFlag.recommendationFlag", source = "sarApplication.isRecommendation")
    public abstract SarReceptionDTO toReceptionDto(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toReceptionDto")
    public abstract void overrideApplicationData(SarReceptionDTO source, @MappingTarget RudiApplicationDTO target);


    @BeforeMapping
    public void beforeOverride(SarReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
        if (Objects.isNull(target.getApplication())) {
            target.setApplication(new ApplicationDTO());
        }

        ApplicationDTO application = target.getApplication();
        application.setApplicationType(new ApplicationTypeDTO(ApplicationSubType.RUDI_SAR.appType()));
        application.setApplicationSubtype(new ApplicationSubtypeDTO(ApplicationSubType.RUDI_SAR.appSubType()));
    }

    @AfterMapping
    public void afterOverride(SarReceptionDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            if (Objects.isNull(source.getDiplomaOwnerId())) {
                trainingCourse.setDiplomaOwner(null);
            }
        }
        target.getApplication().setDocumentReceiveMethods(DocumentReceiveMethodUtils.convertToApplicationDocumentReceiveMethod(source.getDocumentReceiveMethod()));;
    }

    @AfterMapping
    public void afterToReception(RudiApplicationDTO source, @MappingTarget SarReceptionDTO target) {
        super.afterToReception(source, target);
        target.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(source.getApplication().getDocumentReceiveMethods()));
    }

}
