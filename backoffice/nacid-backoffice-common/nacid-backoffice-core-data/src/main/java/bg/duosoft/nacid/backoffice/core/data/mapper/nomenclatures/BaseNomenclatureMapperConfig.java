package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 13:36
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
uses = IntegerToBooleanMapper.class)

public abstract class BaseNomenclatureMapperConfig<E extends NomenclatureEntityBase, D extends NomenclatureBase> {
    @Mapping(target = "isActive", source = "active")
    public abstract NomenclatureBase toDtoConfig(NomenclatureEntityBase e);

    @Mapping(target = "active", source = "isActive")
    public abstract NomenclatureEntityBase toEntityConfig(NomenclatureBase d);
}
