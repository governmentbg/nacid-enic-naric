package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class FacultyMapper extends BaseObjectMapper<FacultyEntity, FacultyDTO> {
}
