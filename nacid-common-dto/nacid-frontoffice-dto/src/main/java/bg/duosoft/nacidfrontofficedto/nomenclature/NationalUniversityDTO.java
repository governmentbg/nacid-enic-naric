package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NationalUniversityDTO extends NomenclatureBaseImpl<String> {
    private String id;
    private String name;
    private String nameEn;
    private SettlementDTO settlement;
    private String address;
    private String addressEn;
    private String zipCode;
    private String website;
    private String logoRelativePath;
    private Boolean isActive;
}
