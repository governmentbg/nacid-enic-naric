package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveOptionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveOptionDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, DocumentReceiveOptionKindMapper.class})
public abstract class DocumentReceiveOptionMapper extends BaseNomenclatureMapper<DocumentReceiveOptionEntity, DocumentReceiveOptionDTO> {

    @Mapping(target = "isActive", source = "active")
    public abstract DocumentReceiveOptionDTO toDto(DocumentReceiveOptionEntity entity);
}
