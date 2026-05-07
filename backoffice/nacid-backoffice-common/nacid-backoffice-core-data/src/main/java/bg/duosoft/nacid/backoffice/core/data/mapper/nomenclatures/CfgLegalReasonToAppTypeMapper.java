package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgLegalReasonToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgLegalReasonToAppTypeDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * User: ggeorgiev
 * Date: 27.06.2023
 */
@Mapper(componentModel = "spring", uses = {ApplicationTypeMapper.class, ApplicationSubtypeMapper.class, LegalReasonMapper.class})
public abstract class CfgLegalReasonToAppTypeMapper extends BaseObjectMapper<CfgLegalReasonToAppTypeEntity, CfgLegalReasonToAppTypeDTO> {
    @Mapping(target = "pk.ateCode", source = "applicationType.id")
    @Mapping(target = "pk.aseCode", source = "applicationSubtype.id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    public abstract CfgLegalReasonToAppTypeEntity toEntity(CfgLegalReasonToAppTypeDTO dto);

    @AfterMapping
    public void afterToEntity(CfgLegalReasonToAppTypeDTO source, @MappingTarget CfgLegalReasonToAppTypeEntity target) {

    }


}
