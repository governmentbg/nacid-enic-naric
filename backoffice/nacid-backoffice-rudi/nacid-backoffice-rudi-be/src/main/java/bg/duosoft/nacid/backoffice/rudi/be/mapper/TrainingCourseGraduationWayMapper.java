package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseGraduationWayDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseGraduationWayEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class
})
public abstract class TrainingCourseGraduationWayMapper extends BaseObjectMapper<TrainingCourseGraduationWayEntity, TrainingCourseGraduationWayDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "trainingCourseId", source = "trainingCourse.id")
    @Mapping(target = "graduationWay", source = "graduationWay")
    @Mapping(target = "notes", source = "notes")
    public abstract TrainingCourseGraduationWayDTO toDto(TrainingCourseGraduationWayEntity entity);

    @InheritInverseConfiguration
    public abstract TrainingCourseGraduationWayEntity toEntity(TrainingCourseGraduationWayDTO dto);

    @AfterMapping
    protected void afterToEntity(TrainingCourseGraduationWayDTO source, @MappingTarget TrainingCourseGraduationWayEntity target) {
        ReferenceDataUtils.setDefaultDomain(target.getGraduationWay(), ReferenceDataDomain.GRADUATION_WAY);
    }

    @AfterMapping
    protected void afterToDto(TrainingCourseGraduationWayEntity source, @MappingTarget TrainingCourseGraduationWayDTO target) {
        ReferenceDataUtils.setDefaultDomain(target.getGraduationWay(), ReferenceDataDomain.GRADUATION_WAY);
    }

}
