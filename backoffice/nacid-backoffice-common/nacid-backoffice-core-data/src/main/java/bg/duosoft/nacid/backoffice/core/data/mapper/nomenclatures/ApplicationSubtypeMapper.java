package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:32
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ApplicationTypeMapper.class})
public abstract class ApplicationSubtypeMapper extends BaseNomenclatureMapper<ApplicationSubtypeEntity, ApplicationSubtypeDTO> {
}
