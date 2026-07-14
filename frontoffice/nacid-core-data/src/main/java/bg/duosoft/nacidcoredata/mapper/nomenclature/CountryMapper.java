package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 20.04.2022
 * Time: 13:11
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class CountryMapper extends BaseNomenclatureMapper<CountryEntity, CountryDTO>{
}
