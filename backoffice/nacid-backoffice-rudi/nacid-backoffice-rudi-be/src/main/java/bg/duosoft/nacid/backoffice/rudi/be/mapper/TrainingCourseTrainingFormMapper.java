package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseTrainingFormDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseTrainingFormEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        ReferenceDataMapper.class
})
public abstract class TrainingCourseTrainingFormMapper extends BaseObjectMapper<TrainingCourseTrainingFormEntity, TrainingCourseTrainingFormDTO> {

    @Mapping(target = "trainingCourseId", source = "id")
    @Mapping(target = "trainingForm", source = "trainingForm")
    @Mapping(target = "notes", source = "notes")
    public abstract TrainingCourseTrainingFormDTO toDto(TrainingCourseTrainingFormEntity entity);

    @InheritInverseConfiguration
    public abstract TrainingCourseTrainingFormEntity toEntity(TrainingCourseTrainingFormDTO dto);

    @AfterMapping
    protected void afterToEntity(TrainingCourseTrainingFormDTO source, @MappingTarget TrainingCourseTrainingFormEntity target) {

    }

    @AfterMapping
    protected void afterToDto(TrainingCourseTrainingFormEntity source, @MappingTarget TrainingCourseTrainingFormDTO target) {

    }

    @BeforeMapping
    protected void beforeToEntity(TrainingCourseTrainingFormDTO source, @MappingTarget TrainingCourseTrainingFormEntity target) {
        this.overrideDtoData(source);
    }

    public void overrideDtoData(TrainingCourseTrainingFormDTO dto) {
        if (Objects.nonNull(dto)) {
            ReferenceDataUtils.setDefaultDomain(dto.getTrainingForm(), ReferenceDataDomain.TRAINING_FORM);
        }
    }

}
