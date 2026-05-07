package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.SchoolSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SchoolSubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SchoolSubjectMapper extends BaseMapper<SchoolSubjectEntity, SchoolSubjectDto> {
}
