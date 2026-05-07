package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveOptionKindEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveOptionKindDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:13
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class DocumentReceiveOptionKindMapper extends BaseNomenclatureMapper<DocumentReceiveOptionKindEntity, DocumentReceiveOptionKindDTO>{

    @Mapping(target = "isActive", source = "active")
    public abstract DocumentReceiveOptionKindDTO toDto(DocumentReceiveOptionKindEntity entity);
}
