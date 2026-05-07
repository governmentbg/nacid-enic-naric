package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgServiceTypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgServiceTypeDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:55
 */
@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, ApplicationTypeMapper.class, ApplicationSubtypeMapper.class })
public abstract class CfgServiceTypeMapper extends BaseObjectMapper<CfgServiceTypeEntity, CfgServiceTypeDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "applicationType", source = "applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "applicationSubtypeCode")
    public abstract CfgServiceTypeDTO toDto(CfgServiceTypeEntity serviceType);

    @InheritInverseConfiguration
    public abstract CfgServiceTypeEntity toEntity(CfgServiceTypeDTO serviceType);
}
