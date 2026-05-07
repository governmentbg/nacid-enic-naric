package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgServiceTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgServiceTypeDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Objects;


@Mapper(componentModel = "spring", uses = {
        ApplicationSubtypeMapper.class,
        ApplicationTypeMapper.class,
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class}
)
public abstract class CfgServiceTypeMapper extends BaseObjectMapper<CfgServiceTypeEntity, CfgServiceTypeDTO> {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "serviceType", source = "serviceType")
    @Mapping(target = "executionDays", source = "executionDays")
    @Mapping(target = "executionDaysType", source = "executionDaysType")
    public abstract CfgServiceTypeDTO toDto(CfgServiceTypeEntity e);


    @AfterMapping
    protected void afterMapping(CfgServiceTypeDTO source, @MappingTarget CfgServiceTypeEntity target) {
        if (Objects.nonNull(target.getApplicationSubtype()) && !StringUtils.hasText(target.getApplicationSubtype().getId())){
            target.setApplicationSubtype(null);
        }
        if (Objects.nonNull(target.getServiceType())){
            target.getServiceType().getPk().setDomain(ReferenceDataDomain.SERVICE_TYPE.domain());
        }
        if (Objects.nonNull(target.getExecutionDaysType())){
            target.getExecutionDaysType().getPk().setDomain(ReferenceDataDomain.EXECUTION_DAYS_TYPE.domain());
        }
    }
}
