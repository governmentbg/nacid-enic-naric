package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.BolognaCycleEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BolognaCycleDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class BolognaCycleMapper extends BaseNomenclatureMapper<BolognaCycleEntity, BolognaCycleDTO> {
}
