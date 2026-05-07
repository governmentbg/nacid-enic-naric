package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgSarAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:56
 */
@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, IntegerToBooleanMapper.class})
public abstract class CfgSarAppStatusMapper extends BaseObjectMapper<CfgSarAppStatusEntity, CfgSarAppStatusDTO> {
    @Mapping(target = "isPositive", source = "positiveFlag")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "sarApplicationType", source = "sarApplicationType")
    public abstract CfgSarAppStatusDTO toDto(CfgSarAppStatusEntity e);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "pk.sarAte", source = "sarApplicationType.id")
    @Mapping(target = "pk.statusCode", source = "status.id")
    public abstract CfgSarAppStatusEntity toEntity(CfgSarAppStatusDTO d);
}
