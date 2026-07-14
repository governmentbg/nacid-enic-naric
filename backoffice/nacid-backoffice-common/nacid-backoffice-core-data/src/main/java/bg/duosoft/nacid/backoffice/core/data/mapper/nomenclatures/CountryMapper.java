package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 13:45
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class CountryMapper extends BaseNomenclatureMapper<CountryEntity, CountryDTO>{
}
