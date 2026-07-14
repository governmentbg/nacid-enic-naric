package bg.duosoft.nacidservicesbe.mapper.common.document;

import bg.duosoft.nacidcoredata.mapper.nomenclature.DocTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.document.AttachedDocumentDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.11.2022
 * Time: 15:26
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        DocTypeMapper.class,
        AttachedFileMapper.class
})
public abstract class AttachedDocumentMapper extends BaseObjectMapper<ApplicationAttachedDocEntity, AttachedDocumentDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "docType", source = "attachmentType")
    @Mapping(target = "copyType", source = "attachmentForm")
    @Mapping(target = "attachment", source = "file")
    public abstract ApplicationAttachedDocEntity toEntity(AttachedDocumentDTO attachedDocumentDTO);

    @InheritInverseConfiguration
    public abstract AttachedDocumentDTO toDto(ApplicationAttachedDocEntity applicationAttachedDocEntity);

    public DocumentDetailsDTO toDocumentDetailsDtoFromEntityList(List<ApplicationAttachedDocEntity> applicationAttachedDocEntityList){
        DocumentDetailsDTO documentDetails = new DocumentDetailsDTO();
        documentDetails.setAttachments(toDtoList(applicationAttachedDocEntityList));
        return documentDetails;
    }
}
