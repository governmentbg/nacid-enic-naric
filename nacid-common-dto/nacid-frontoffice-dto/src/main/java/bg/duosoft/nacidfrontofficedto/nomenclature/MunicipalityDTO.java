package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MunicipalityDTO extends NomenclatureBaseImpl<String> {
    private String districtcode;
    private String code2;
    private String mainsettlementcode;
    private String category;
    private String alias;
    private String description;
    private Integer version;
    private String nameen;

}
