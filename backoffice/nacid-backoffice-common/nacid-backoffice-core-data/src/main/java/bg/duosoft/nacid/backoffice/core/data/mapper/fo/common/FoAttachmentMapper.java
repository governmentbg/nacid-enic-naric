package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class FoAttachmentMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fileSize", source = "fileSize")
    @Mapping(target = "contentType", source = "contentType")
    @Mapping(target = "fileName", source = "fileName")
    public abstract AttachmentDTO toBackofficeObject(FileStoreEntryDTO source);
}
