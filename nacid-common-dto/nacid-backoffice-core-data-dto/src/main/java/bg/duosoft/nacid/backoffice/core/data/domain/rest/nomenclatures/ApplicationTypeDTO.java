package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:23
 */
@Data
public class ApplicationTypeDTO extends StringKeyNomenclatureBase {
    public ApplicationTypeDTO(String id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public ApplicationTypeDTO() {
    }

    public ApplicationTypeDTO(String id) {
        setId(id);
    }
}
