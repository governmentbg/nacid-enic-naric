package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDTO;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradingScaleEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class GradingScaleMapper extends BaseNomenclatureMapper<GradingScaleEntity, GradingScaleDTO> {

}
