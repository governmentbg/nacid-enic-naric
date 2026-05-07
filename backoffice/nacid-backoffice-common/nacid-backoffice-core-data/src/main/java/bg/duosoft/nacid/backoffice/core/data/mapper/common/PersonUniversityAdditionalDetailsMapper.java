package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonUniversityAdditionalDetailsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonUniversityAdditionalDetailsDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PersonUniversityAdditionalDetailsMapper extends BaseObjectMapper<PersonUniversityAdditionalDetailsEntity, PersonUniversityAdditionalDetailsDTO> {
    public abstract PersonUniversityAdditionalDetailsDTO toDto(PersonUniversityAdditionalDetailsEntity e);

    @InheritInverseConfiguration
    public abstract PersonUniversityAdditionalDetailsEntity toEntity(PersonUniversityAdditionalDetailsDTO dto);

}
