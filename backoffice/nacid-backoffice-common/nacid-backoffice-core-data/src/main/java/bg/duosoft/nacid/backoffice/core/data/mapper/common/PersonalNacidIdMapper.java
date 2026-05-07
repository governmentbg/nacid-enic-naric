package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonalNacidIdEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonalNacidIdDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PersonalNacidIdMapper extends BaseObjectMapper<PersonalNacidIdEntity, PersonalNacidIdDTO> {
}
