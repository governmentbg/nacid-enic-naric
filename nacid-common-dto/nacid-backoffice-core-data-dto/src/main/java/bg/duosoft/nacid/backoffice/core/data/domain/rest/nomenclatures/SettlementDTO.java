package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBaseImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

//COPIED FROM NACID-CORE-DATA
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementDTO extends NomenclatureBaseImpl<String> {
    public SettlementDTO(String id) {
        super.id = id;
    }

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
    private String simpleSettlementName;
}