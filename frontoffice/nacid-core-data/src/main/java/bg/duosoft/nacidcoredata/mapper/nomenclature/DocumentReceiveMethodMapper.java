package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocumentReceiveMethodEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class DocumentReceiveMethodMapper extends BaseNomenclatureMapper<DocumentReceiveMethodEntity, DocumentReceiveMethodDTO> {

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "defaultValue", source = "defaultFlag")
    public abstract DocumentReceiveMethodDTO toDto(DocumentReceiveMethodEntity entity);
}
