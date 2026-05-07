package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapping;

public abstract class BaseNomenclatureMapper<E extends NomenclatureEntityBase, D extends NomenclatureBase> extends BaseObjectMapper<E, D> {
    @Mapping(target = "isActive", source = "active")
    public abstract D toDto(E e);
}
