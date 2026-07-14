package bg.duosoft.nacidcoredata.mapper.nomenclature;


import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkMunicipalityEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.MunicipalityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class MunicipalityMapper extends BaseNomenclatureMapper<EkMunicipalityEntity, MunicipalityDTO> {
}
