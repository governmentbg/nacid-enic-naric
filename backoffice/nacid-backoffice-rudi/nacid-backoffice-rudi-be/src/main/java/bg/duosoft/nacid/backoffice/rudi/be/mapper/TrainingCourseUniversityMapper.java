package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseUniversityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {UniversityMapper.class, FacultyMapper.class})
public abstract class TrainingCourseUniversityMapper extends BaseObjectMapper<TrainingCourseUniversityEntity, TrainingCourseUniversityDTO> {

    @Mapping(target = "university", source = "pk.university")
    @Mapping(target = "universityNameTranslated", source = "universityNameTranslated")
    @Mapping(target = "universityContact", source = "universityContact")
    @Mapping(target = "ordNum", source = "ordNum")
    @Mapping(target = "faculty", source = "faculty")
    public abstract TrainingCourseUniversityDTO toDto(TrainingCourseUniversityEntity entity);

    @AfterMapping
    protected void afterToDto(@MappingTarget TrainingCourseUniversityDTO dto) {
        dto.setCountry(dto.getUniversity().getCountry());
    }

    @AfterMapping
    protected void afterToEntity(@MappingTarget TrainingCourseUniversityEntity entity) {
        if (Objects.nonNull(entity.getFaculty()) && Objects.isNull(entity.getFaculty().getId())) {
            entity.setFaculty(null);
        }
    }

}
