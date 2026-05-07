package bg.duosoft.nacidservicesbe.mapper.documentdelivery;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicEntryDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.DocumentDeliveryDetailsEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 12:13
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class DocBibliographicEntryDetailsMapper extends BaseObjectMapper<DocumentDeliveryDetailsEntity, DocBibliographicEntryDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "bibliographicData", source = "bibliographicDataText")
    @Mapping(target = "digitalCatalogue", source = "electronicCatalogues")
    @Mapping(target = "bgLibrary", source = "bgLibraries")
    @Mapping(target = "foreignLibrary", source = "foreignLibraries")
    @Mapping(target = "documentDeliveryCopyType", source = "deliveryResultKind")
    @Mapping(target = "fileId", source = "file.fileId")
    @Mapping(target = "fileName", source = "file.fileName")
    @Mapping(target = "relativePath", source = "file.relativePath")
    @Mapping(target = "rootDirectory", source = "file.rootDirectory")
    public abstract DocumentDeliveryDetailsEntity toEntity(DocBibliographicEntryDetailsDTO docBibliographicEntryDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract DocBibliographicEntryDetailsDTO toDto(DocumentDeliveryDetailsEntity documentDeliveryDetailsEntity);
}
