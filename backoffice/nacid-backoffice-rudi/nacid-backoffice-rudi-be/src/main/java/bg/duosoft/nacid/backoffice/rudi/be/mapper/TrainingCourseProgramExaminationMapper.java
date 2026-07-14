package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseProgramExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.*;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseProgramExaminationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        ReferenceDataMapper.class,
})
public abstract class TrainingCourseProgramExaminationMapper extends BaseObjectMapper<TrainingCourseProgramExaminationEntity, TrainingCourseProgramExaminationDTO>  {

    @Mapping(target = "isLegitimate", source = "legitimateFlag")
    @Mapping(target = "programType", source = "programType")
    public abstract TrainingCourseProgramExaminationDTO toDto(TrainingCourseProgramExaminationEntity e);
}
