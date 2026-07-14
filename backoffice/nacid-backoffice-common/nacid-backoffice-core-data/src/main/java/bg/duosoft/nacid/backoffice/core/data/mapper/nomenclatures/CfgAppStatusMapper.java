package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAppStatusDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:56
 */
@Mapper(componentModel = "spring", uses = {ApplicationSubtypeMapper.class, ApplicationTypeMapper.class, ReferenceDataMapper.class, IntegerToBooleanMapper.class})
public abstract class CfgAppStatusMapper extends BaseObjectMapper<CfgAppStatusEntity, CfgAppStatusDTO> {
    @Mapping(target = "isLegal", source = "legalFlag")
    @Mapping(target = "isCommission", source = "commissionFlag")
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "isInitialStatus", source = "initialStatusFlag")
    @Mapping(target = "isExecutionSuspendedStatus", source = "executionSuspendedFlag")
    public abstract CfgAppStatusDTO toDto(CfgAppStatusEntity e);

    @AfterMapping
    public void afterToEntity(CfgAppStatusDTO source, @MappingTarget CfgAppStatusEntity target) {
        ApplicationSubtypeDTO applicationSubtype = source.getApplicationSubtype();
        if (Objects.nonNull(applicationSubtype) && !StringUtils.hasText(applicationSubtype.getId())) {
            target.setApplicationSubtype(null);
        }
    }
}
