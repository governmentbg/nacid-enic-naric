package bg.duosoft.nacidservicesbe.mapper.common.document;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.11.2022
 * Time: 15:29
 */
@Mapper(componentModel = "spring")
public abstract class AttachedFileMapper extends BaseObjectMapper<AttachmentEntity, FileStoreEntryDTO> {


    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fileId", source = "fileId")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "relativePath", source = "relativePath")
    @Mapping(target = "rootDirectory", source = "rootDirectory")
    public abstract AttachmentEntity toEntity(FileStoreEntryDTO fileStoreEntryDTO);

    @InheritInverseConfiguration
    public abstract FileStoreEntryDTO toDto(AttachmentEntity attachmentEntity);
}
