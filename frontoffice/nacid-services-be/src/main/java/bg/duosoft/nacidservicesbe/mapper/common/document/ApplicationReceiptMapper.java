package bg.duosoft.nacidservicesbe.mapper.common.document;

import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.document.ApplicationReceiptDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationReceiptEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.01.2023
 * Time: 18:45
 */
@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        FoApplicationStatusMapper.class,
})
public abstract class ApplicationReceiptMapper extends BaseObjectMapper<ApplicationReceiptEntity, ApplicationReceiptDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fileId", source = "file.fileId")
    @Mapping(target = "fileName", source = "file.fileName")
    @Mapping(target = "rootDirectory", source = "file.rootDirectory")
    @Mapping(target = "relativePath", source = "file.relativePath")
    @Mapping(target = "statusCode", source = "status")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "dateCreated", source = "dateCreated")
    public abstract ApplicationReceiptEntity toEntity(ApplicationReceiptDTO applicationReceiptDTO);

    @InheritInverseConfiguration
    public abstract ApplicationReceiptDTO toDto(ApplicationReceiptEntity applicationReceiptEntity);
}
