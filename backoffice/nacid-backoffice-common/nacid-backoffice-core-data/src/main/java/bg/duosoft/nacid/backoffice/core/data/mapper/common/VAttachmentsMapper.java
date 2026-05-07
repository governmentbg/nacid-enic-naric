package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.VAttachmentsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentsViewDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationSubtypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class,
})
public abstract class VAttachmentsMapper extends BaseObjectMapper<VAttachmentsEntity, AttachmentsViewDTO> {

    @Mapping(target = "attachmentId", source = "attachmentId")
    @Mapping(target = "applicationId", source = "applicationId")
    @Mapping(target = "docTypeName", source = "docTypeName")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "bucketName", source = "bucketName")
    @Mapping(target = "fileLocation", source = "fileLocation")
    @Mapping(target = "contentType", source = "contentType")
    public abstract AttachmentsViewDTO toDto(VAttachmentsEntity entity);

}
