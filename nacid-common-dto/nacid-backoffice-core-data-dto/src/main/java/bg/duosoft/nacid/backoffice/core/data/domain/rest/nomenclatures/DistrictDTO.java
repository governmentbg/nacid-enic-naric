package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBaseImpl;
import lombok.*;

//COPIED FROM NACID-CORE-DATA
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