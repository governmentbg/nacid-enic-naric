package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:08
 */
@Mapper(componentModel = "spring", uses = {
        DocTypeMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class
})
public abstract class CfgDocTypeMapper extends BaseObjectMapper<CfgDocTypeEntity, CfgDocTypeDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "applicationType", source = "applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "applicationSubtypeCode")
    public abstract CfgDocTypeDTO toDto(CfgDocTypeEntity cfgDocTypeEntity);
}
