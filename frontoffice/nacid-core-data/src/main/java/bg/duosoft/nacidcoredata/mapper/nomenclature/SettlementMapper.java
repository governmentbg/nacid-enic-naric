package bg.duosoft.nacidcoredata.mapper.nomenclature;


import bg.duosoft.nacidcoredata.domain.entity.nomenclature.EkSettlementEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MunicipalityMapper.class, DistrictMapper.class, IntegerToBooleanMapper.class})
public abstract class SettlementMapper extends BaseNomenclatureMapper<EkSettlementEntity, SettlementDTO> {

    @AfterMapping
    protected void afterToDto(EkSettlementEntity source, @MappingTarget SettlementDTO target) {
        String fullSettlementName = "";
        if(target.getName() != null && target.getMunicipalitycode() != null && target.getDistrictcode() != null) {
            fullSettlementName = target.getName() + ", " + target.getMunicipalitycode().getName() + ", " + target.getDistrictcode().getName();
        }
        target.setFullSettlementName(target.getDistrict() != null && target.getDistrict() ? target.getName() : fullSettlementName);
    }
}
