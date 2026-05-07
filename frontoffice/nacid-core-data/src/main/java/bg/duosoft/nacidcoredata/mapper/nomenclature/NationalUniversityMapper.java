package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.NationalUniversityEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SettlementMapper.class, IntegerToBooleanMapper.class})
public abstract class NationalUniversityMapper extends BaseNomenclatureMapper<NationalUniversityEntity, NationalUniversityDTO> {

}
