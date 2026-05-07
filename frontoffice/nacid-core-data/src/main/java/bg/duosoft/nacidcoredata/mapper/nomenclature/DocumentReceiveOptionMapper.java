package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocumentReceiveOptionEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveOptionDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, DocumentReceiveOptionKindMapper.class})
public abstract class DocumentReceiveOptionMapper extends BaseNomenclatureMapper<DocumentReceiveOptionEntity, DocumentReceiveOptionDTO> {

    @Mapping(target = "isActive", source = "active")
    public abstract DocumentReceiveOptionDTO toDto(DocumentReceiveOptionEntity entity);
}
