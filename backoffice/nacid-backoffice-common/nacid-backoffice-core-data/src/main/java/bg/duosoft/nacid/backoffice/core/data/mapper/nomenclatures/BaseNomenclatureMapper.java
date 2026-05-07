package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapping;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 13:36
 */

public abstract class BaseNomenclatureMapper<E extends NomenclatureEntityBase, D extends NomenclatureBase> extends BaseObjectMapper<E, D> {
    @Mapping(target = "isActive", source = "active")
    public abstract D toDto(E e);
}
