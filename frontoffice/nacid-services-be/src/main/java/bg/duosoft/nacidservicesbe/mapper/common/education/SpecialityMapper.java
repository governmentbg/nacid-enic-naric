package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidfrontofficedto.services.common.education.SpecialityDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseSpecialityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.11.2022
 * Time: 15:49
 */
@Mapper(componentModel = "spring")
public abstract class SpecialityMapper extends BaseObjectMapper<RudiTrainingCourseSpecialityEntity, SpecialityDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "speciality", source = "name")
    @Mapping(target = "originalSpeciality", source = "originalName")
    @Mapping(target = "id", source = "id", ignore = true)
    public abstract RudiTrainingCourseSpecialityEntity toEntity(SpecialityDTO s);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id", ignore = true)
    public abstract SpecialityDTO toDto(RudiTrainingCourseSpecialityEntity rudiTrainingCourseSpecialityEntity);

}
