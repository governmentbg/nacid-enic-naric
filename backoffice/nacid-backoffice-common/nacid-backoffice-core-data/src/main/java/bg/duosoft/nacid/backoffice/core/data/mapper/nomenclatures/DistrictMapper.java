package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkDistrictEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DistrictDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class DistrictMapper extends BaseNomenclatureMapper<EkDistrictEntity, DistrictDTO> {

}
