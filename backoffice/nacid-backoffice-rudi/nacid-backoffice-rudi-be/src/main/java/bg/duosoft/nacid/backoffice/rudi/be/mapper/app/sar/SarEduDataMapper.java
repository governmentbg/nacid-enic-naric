package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.education.SarEduDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperConfig;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = EduDataMapperConfig.class)
public abstract class SarEduDataMapper extends EduDataMapperBase<SarEduDataDTO> {

    @InheritConfiguration(name = "baseEducationDataSectionMapping")
    @Mapping(target = "trainingCourseSpecialities", source = "trainingCourse.trainingCourseSpecialities")
    @Mapping(target = "qualification", source = "trainingCourse.qualification")
    @Mapping(target = "originalQualification", source = "trainingCourse.originalQualification")
    @Mapping(target = "schoolCountry", source = "trainingCourse.schoolCountry")
    @Mapping(target = "schoolCity", source = "trainingCourse.schoolCity")
    @Mapping(target = "schoolName", source = "trainingCourse.schoolName")
    @Mapping(target = "schoolNotes", source = "trainingCourse.schoolNotes")
    @Mapping(target = "schoolGraduationDate", source = "trainingCourse.schoolGraduationDate")
    public abstract SarEduDataDTO toEducationDataSection(RudiApplicationDTO application);


    @InheritInverseConfiguration(name = "toEducationDataSection")
    public abstract void overrideApplicationData(SarEduDataDTO source, @MappingTarget RudiApplicationDTO target);


    @AfterMapping
    public void afterOverride(SarEduDataDTO source, @MappingTarget RudiApplicationDTO target) {
        commonSarDiplomaRecAfterOverride(source, target);
    }

    @AfterMapping
    public void afterToEducationDataSection(RudiApplicationDTO source, @MappingTarget SarEduDataDTO target) {
        commonSarDiplomaRecAfterOverrideInverse(source, target);
    }

}
