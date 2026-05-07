package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgEduLevelEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgEduLevelDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 16:28
 */
@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, ApplicationTypeMapper.class, ApplicationSubtypeMapper.class})
public abstract class CfgEduLevelMapper extends BaseObjectMapper<CfgEduLevelEntity, CfgEduLevelDTO> {

    @Mapping(target = "applicationType", source = "id.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "id.applicationSubtypeCode")
    public abstract CfgEduLevelDTO toDto(CfgEduLevelEntity cfgEduLevelEntity);

    @InheritInverseConfiguration
    public abstract CfgEduLevelEntity toEntity(CfgEduLevelDTO cfgEduLevelDTO);
}
