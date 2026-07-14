package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.program;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.program.ProgramExamSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public abstract class ProgramExamDataMapper {
    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "isLegitimate", source = "trainingCourse.programExamination.isLegitimate", defaultValue = "false")
    @Mapping(target = "programTypeId", source = "trainingCourse.programExamination.programType.id")
    public abstract ProgramExamSectionDTO toProgramExamSection(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toProgramExamSection")
    public abstract void overrideApplicationData(ProgramExamSectionDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(ProgramExamSectionDTO source, @MappingTarget RudiApplicationDTO target) {
       if (StringUtils.hasText(source.getProgramTypeId())) {
           ReferenceDataUtils.setDefaultDomain(target.getTrainingCourse().getProgramExamination().getProgramType(), ReferenceDataDomain.TRAINING_PROGRAM_TYPE);
       }
    }

}
