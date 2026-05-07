package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAdditionalAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationAdditionalAttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        DocumentTypeMapper.class,
        ReferenceDataMapper.class,
        AttachmentMapper.class
})
public abstract class ApplicationAdditionalAttachedDocMapper extends BaseObjectMapper<ApplicationAdditionalAttachedDocEntity, ApplicationAdditionalAttachedDocDTO> {
}
