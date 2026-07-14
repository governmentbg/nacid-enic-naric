package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationPropertyEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ApplicationPropertiesMapper extends BaseObjectMapper<ApplicationPropertyEntity, ApplicationPropertyDTO> {
}
