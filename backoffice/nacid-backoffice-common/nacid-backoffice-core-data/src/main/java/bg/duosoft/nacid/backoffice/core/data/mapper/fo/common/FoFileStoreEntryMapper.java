package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FoFileStoreEntryMapper {

    @Mapping(target = "fileId", source = "fileId")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "fileSize", source = "fileSize")
    @Mapping(target = "contentType", source = "contentType")
    @Mapping(target = "rootDirectory", source = "rootDirectory")
    @Mapping(target = "relativePath", source = "relativePath")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "additionalMetadata", source = "additionalMetadata")
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO toBackofficeObject(FileStoreEntryDTO source);
}
