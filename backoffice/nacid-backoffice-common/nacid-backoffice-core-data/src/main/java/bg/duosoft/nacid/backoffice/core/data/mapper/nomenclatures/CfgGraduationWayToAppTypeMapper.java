package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationWayToAppTypeDTO;
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
public abstract class CfgGraduationWayToAppTypeMapper extends BaseObjectMapper<CfgGraduationWayToAppTypeEntity, CfgGraduationWayToAppTypeDTO> {
    @Mapping(target = "pk.ateCode", source = "applicationType.id")
    @Mapping(target = "pk.aseCode", source = "applicationSubtype.id")
    @Mapping(target = "pk.gwyCode", source = "graduationWay.id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "graduationWay", source = "graduationWay")
    public abstract CfgGraduationWayToAppTypeEntity toEntity(CfgGraduationWayToAppTypeDTO dto);

    @AfterMapping
    public void afterToEntity(CfgGraduationWayToAppTypeDTO source, @MappingTarget CfgGraduationWayToAppTypeEntity target) {
        if (Objects.nonNull(target.getGraduationWay())) {
            ReferenceDataUtils.setDefaultDomain(target.getGraduationWay(), ReferenceDataDomain.GRADUATION_WAY);
        }
    }


}
