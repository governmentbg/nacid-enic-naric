package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "newInstance")
public class BaseNomenclatureDTO {
    private String id;
    private String name;
    private String nameEn;
    private Boolean isActive;
    private Integer index;
}
