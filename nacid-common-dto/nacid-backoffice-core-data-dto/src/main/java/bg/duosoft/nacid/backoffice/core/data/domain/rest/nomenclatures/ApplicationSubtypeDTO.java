package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:23
 */
@Data
@NoArgsConstructor
public class ApplicationSubtypeDTO extends StringKeyNomenclatureBase {
    private ApplicationTypeDTO applicationType;
    public ApplicationSubtypeDTO(String id) {
        this.id = id;
    }
    public ApplicationSubtypeDTO(String id, String name, Boolean isActive, ApplicationTypeDTO applicationType) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.applicationType = applicationType;
    }
}
