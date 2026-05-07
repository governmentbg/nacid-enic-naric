package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ExternalNomenclaturesMapEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 20.07.2022
 * Time: 16:45
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ExternalNomenclaturesMapMapper extends BaseObjectMapper<ExternalNomenclaturesMapEntity, ExternalNomenclaturesMapDTO> {
}
