package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseSpecialityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TrainingCourseSpecialityMapper extends BaseObjectMapper<TrainingCourseSpecialityEntity, TrainingCourseSpecialityDTO> {
}
