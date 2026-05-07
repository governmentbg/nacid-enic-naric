package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.main.SarMainDataDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicantDiplomaNamesMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main.MainDataMapperConfig;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", config = MainDataMapperConfig.class, uses = {
        IntegerToBooleanMapper.class,
        ApplicantDiplomaNamesMapper.class
})
public abstract class SarMainDataMapper extends MainDataMapperBase<SarMainDataDTO> {

    @InheritConfiguration(name = "toMainDataSectionBase")
    @Mapping(target = "diplomaOwnerId", source = "trainingCourse.diplomaOwner.id")
    @Mapping(target = "diplomaOwnerEan", source = "trainingCourse.diplomaOwnerEan")
    @Mapping(target = "outgoingNumber", source = "sarApplication.outgoingNumber")
    @Mapping(target = "internalNumber", source = "sarApplication.internalNumber")
    public abstract SarMainDataDTO toMainDataSection(RudiApplicationDTO application);

    @AfterMapping
    public void afterToMainDataSection(RudiApplicationDTO source, @MappingTarget SarMainDataDTO target) {
        super.afterToMainDataSection(source, target);
        target.setDocumentReceiveMethod(DocumentReceiveMethodUtils.convertToDocumentReceiveMethodForm(source.getApplication().getDocumentReceiveMethods()));
    }

    @InheritInverseConfiguration(name = "toMainDataSection")
    public abstract void overrideApplicationData(SarMainDataDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(SarMainDataDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            if (Objects.isNull(source.getDiplomaOwnerId())) {
                trainingCourse.setDiplomaOwner(null);
                trainingCourse.setDiplomaOwnerEan(null);
            }
        }
        target.getApplication().setDocumentReceiveMethods(DocumentReceiveMethodUtils.convertToApplicationDocumentReceiveMethod(source.getDocumentReceiveMethod()));
    }

}
