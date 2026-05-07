package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SettlementDTO extends NomenclatureBaseImpl<String> {
    private MunicipalityDTO municipalitycode;
    private DistrictDTO districtcode;
    private String municipalitycode2;
    private String districtcode2;
    private String typename;
    private String settlementname;
    private String typecode;
    private String mayoraltycode;
    private String category;
    private String altitude;
    private String alias;
    private String description;
    private Boolean district;
    private Integer version;
    private String settlementnameen;
    private Integer postalcode;
    private String fullSettlementName;
}
