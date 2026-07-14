package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentReceiveMethodMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        AddressMapper.class,
        DocumentReceiveMethodMapper.class
})
public abstract class ApplicationDocumentReceiveMethodMapper extends BaseObjectMapper<ApplicationDocumentReceiveMethodEntity, ApplicationDocumentReceiveMethodDTO> {
}
