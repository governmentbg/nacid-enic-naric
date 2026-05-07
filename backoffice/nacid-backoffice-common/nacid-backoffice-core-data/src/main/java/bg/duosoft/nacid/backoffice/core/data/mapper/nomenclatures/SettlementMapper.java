package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.EkSettlementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SettlementDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {MunicipalityMapper.class, DistrictMapper.class, IntegerToBooleanMapper.class})
public abstract class SettlementMapper extends BaseNomenclatureMapper<EkSettlementEntity, SettlementDTO> {

    @Mapping(target = "isActive", source = "active")
    public abstract SettlementDTO toDto(EkSettlementEntity e);

    @InheritInverseConfiguration
    @Mapping(target = "active", source = "isActive", defaultValue = "1")
    @Mapping(target = "district", source = "district", defaultValue = "0")
    public abstract EkSettlementEntity toEntity(SettlementDTO dto);

    @AfterMapping
    protected void afterToDto(EkSettlementEntity source, @MappingTarget SettlementDTO target) {
        target.setFullSettlementName(buildFullSettlementName(target));
        target.setSimpleSettlementName(target.getDistrict() ? target.getName() : target.getFullSettlementName());
    }

    private static String buildFullSettlementName(SettlementDTO target) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getName());
        if (Objects.nonNull(target.getMunicipalitycode())) {
            sb.append(", ");
            sb.append(target.getMunicipalitycode().getName());
        }
        if (Objects.nonNull(target.getDistrictcode())) {
            sb.append(", ");
            sb.append(target.getDistrictcode().getName());
        }
        return sb.toString();
    }
}
