package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgDocTypeRequirementEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:28
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class,
        DocTypeMapper.class
})
public abstract class CfgDocTypeRequirementMapper extends BaseObjectMapper<CfgDocTypeRequirementEntity, CfgDocTypeRequirementDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "applicationType", source = "applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "applicationSubtypeCode")
    public abstract CfgDocTypeRequirementDTO toDto(CfgDocTypeRequirementEntity cfgDocTypeRequirementEntity);

    @InheritInverseConfiguration
    public abstract CfgDocTypeRequirementEntity toEntity(CfgDocTypeRequirementDTO cfgDocTypeRequirementDTO);
}
