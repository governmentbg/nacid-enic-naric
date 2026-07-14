package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
uses = IntegerToBooleanMapper.class)

public abstract class BaseNomenclatureMapperConfig<E extends NomenclatureEntityBase, D extends NomenclatureBase> {
    @Mapping(target = "isActive", source = "active")
    public abstract NomenclatureBase toDtoConfig(NomenclatureEntityBase e);

    @Mapping(target = "active", source = "isActive")
    public abstract NomenclatureEntityBase toEntityConfig(NomenclatureBase d);
}
