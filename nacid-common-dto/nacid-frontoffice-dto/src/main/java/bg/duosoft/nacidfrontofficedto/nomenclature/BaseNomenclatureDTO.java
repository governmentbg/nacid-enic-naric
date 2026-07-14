package bg.duosoft.nacidfrontofficedto.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "newInstance")
@NoArgsConstructor
public class BaseNomenclatureDTO {
    private String id;
    private String name;
    private String nameEn;
    private Boolean isActive;
}
