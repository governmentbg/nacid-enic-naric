package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkDistrictEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.DistrictDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class DistrictMapper extends BaseNomenclatureMapper<EkDistrictEntity, DistrictDTO> {


}
