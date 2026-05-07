package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.speciality;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.SpecialityDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring")
public abstract class FoSpecialityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "speciality", source = "name")
    @Mapping(target = "originalSpeciality", source = "originalName")
    public abstract TrainingCourseSpecialityDTO toTrainingCourseSpeciality(SpecialityDTO source);

    @InheritConfiguration(name = "toTrainingCourseSpeciality")
    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    public abstract List<TrainingCourseSpecialityDTO> toTrainingCourseSpecialityList(List<SpecialityDTO> sourceList);

}
