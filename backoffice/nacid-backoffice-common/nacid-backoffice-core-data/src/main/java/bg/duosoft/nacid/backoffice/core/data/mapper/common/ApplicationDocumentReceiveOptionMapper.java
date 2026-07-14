package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocumentReceiveOptionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveOptionDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentReceiveOptionKindMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentReceiveOptionMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentReceiveOptionMapper.class, DocumentReceiveOptionKindMapper.class,
        AddressMapper.class, IntegerToBooleanMapper.class
})
public abstract class ApplicationDocumentReceiveOptionMapper extends BaseObjectMapper<ApplicationDocumentReceiveOptionEntity, ApplicationDocumentReceiveOptionDTO> {
}
