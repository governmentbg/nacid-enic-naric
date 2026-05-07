package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DistrictDTO extends NomenclatureBaseImpl<String> {
    private String code2;
    private String secondlevelregioncode;
    private String mainsettlementcode;
    private String alias;
    private String description;
    private Integer version;
    private String nameen;
}
