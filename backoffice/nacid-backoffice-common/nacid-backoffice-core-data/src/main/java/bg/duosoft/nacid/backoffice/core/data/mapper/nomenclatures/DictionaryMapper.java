package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DictionaryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DictionaryDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class DictionaryMapper extends BaseNomenclatureMapper<DictionaryEntity, DictionaryDTO>{
}
