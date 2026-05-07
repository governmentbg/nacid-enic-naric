package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingLocationExaminationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingLocationExaminationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
})
public abstract class TrainingLocationExaminationMapper extends BaseObjectMapper<TrainingLocationExaminationEntity, TrainingLocationExaminationDTO> {

    @Mapping(target = "isLegitimate", source = "legitimateFlag")
    public abstract TrainingLocationExaminationDTO toDto(TrainingLocationExaminationEntity e);
}
