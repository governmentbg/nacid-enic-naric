package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
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
public abstract class DocTypeMapper extends BaseNomenclatureMapper<DocTypeEntity, DocTypeDTO>{

    @Mapping(target = "isActive", source = "active")
    public abstract DocTypeDTO toDto(DocTypeEntity entity);
}
