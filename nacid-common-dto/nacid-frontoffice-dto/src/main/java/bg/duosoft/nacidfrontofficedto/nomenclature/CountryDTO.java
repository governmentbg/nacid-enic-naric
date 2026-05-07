package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 20.04.2022
 * Time: 13:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO extends NomenclatureBaseImpl<String> {
    private String id;
    private String name;
    private String nameEn;
    private String officialName;
    private String nativeName;
    private Boolean isActive;
}
