package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgGraduationWayEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgGraduationWayDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 15:29
 */
@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, ApplicationTypeMapper.class, ApplicationSubtypeMapper.class})
public abstract class CfgGraduationWayMapper extends BaseObjectMapper<CfgGraduationWayEntity, CfgGraduationWayDTO> {

    @Mapping(target = "applicationType", source = "id.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "id.applicationSubtypeCode")
    public abstract CfgGraduationWayDTO toDto(CfgGraduationWayEntity cfgGraduationWayEntity);

    @InheritInverseConfiguration
    public abstract CfgGraduationWayEntity toEntity(CfgGraduationWayDTO cfgGraduationWayDTO);
}
