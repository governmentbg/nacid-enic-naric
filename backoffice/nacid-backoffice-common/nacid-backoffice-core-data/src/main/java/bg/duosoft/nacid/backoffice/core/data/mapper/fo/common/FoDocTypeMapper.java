package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FoDocTypeMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract DocumentTypeDTO toBackofficeObject(DocTypeDTO source);
}
