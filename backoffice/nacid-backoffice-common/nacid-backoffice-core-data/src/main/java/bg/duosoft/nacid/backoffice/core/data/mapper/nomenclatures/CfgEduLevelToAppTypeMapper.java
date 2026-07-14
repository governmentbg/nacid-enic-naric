package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgEduLevelToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:14
 */
@Mapper(componentModel = "spring", uses = {ApplicationTypeMapper.class, ApplicationSubtypeMapper.class, ReferenceDataMapper.class})
public abstract class CfgEduLevelToAppTypeMapper extends BaseObjectMapper<CfgEduLevelToAppTypeEntity, CfgEduLevelToAppTypeDTO> {
    @Mapping(target = "pk.ateCode", source = "applicationType.id")
    @Mapping(target = "pk.aseCode", source = "applicationSubtype.id")
    @Mapping(target = "pk.ellCode", source = "educationLevel.id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "educationLevel", source = "educationLevel")
    public abstract CfgEduLevelToAppTypeEntity toEntity(CfgEduLevelToAppTypeDTO dto);

    @AfterMapping
    public void afterToEntity(CfgEduLevelToAppTypeDTO source, @MappingTarget CfgEduLevelToAppTypeEntity target) {
        if (Objects.nonNull(target.getEducationLevel())) {
            ReferenceDataUtils.setDefaultDomain(target.getEducationLevel(), ReferenceDataDomain.EDUCATION_LEVEL);
        }
    }


}
