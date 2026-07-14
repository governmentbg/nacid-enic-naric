package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 13.09.2022
 * Time: 13:21
 */
@Data
@NoArgsConstructor
public class ProfessionExperienceDocumentTypeDTO extends StringKeyNomenclatureBase {
    private Boolean isForExperienceCalculation;

    public ProfessionExperienceDocumentTypeDTO(String id) {
        this.id = id;
    }
}
