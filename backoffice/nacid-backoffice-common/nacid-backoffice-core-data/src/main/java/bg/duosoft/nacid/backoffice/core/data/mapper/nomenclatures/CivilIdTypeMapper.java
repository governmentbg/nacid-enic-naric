package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CivilIdTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:32
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class})
public abstract class CivilIdTypeMapper extends BaseNomenclatureMapper<CivilIdTypeEntity, CivilIdTypeDTO> {
}
