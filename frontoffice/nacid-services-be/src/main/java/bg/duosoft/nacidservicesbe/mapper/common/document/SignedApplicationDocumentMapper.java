package bg.duosoft.nacidservicesbe.mapper.common.document;

import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationReceiptEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.02.2023
 * Time: 15:55
 */
@Mapper(componentModel = "spring", uses = {
        FoApplicationStatusMapper.class,
})
public abstract class SignedApplicationDocumentMapper extends BaseObjectMapper<ApplicationReceiptEntity, SignedApplicationDocumentDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fileId", source = "file.fileId")
    @Mapping(target = "fileName", source = "file.fileName")
    @Mapping(target = "rootDirectory", source = "file.rootDirectory")
    @Mapping(target = "relativePath", source = "file.relativePath")
    @Mapping(target = "statusCode", source = "status")
    public abstract ApplicationReceiptEntity toEntity(SignedApplicationDocumentDTO signedApplicationDocumentDTO);

    @InheritInverseConfiguration
    public abstract SignedApplicationDocumentDTO toDto(ApplicationReceiptEntity receiptEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget ApplicationReceiptEntity target, SignedApplicationDocumentDTO source){
        target.setActive(1);
        target.setDateCreated(LocalDateTime.now());
    }
}
