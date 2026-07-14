package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EuropeanQualificationFrameworkDTO extends IntegerKeyNomenclatureBase {
    public EuropeanQualificationFrameworkDTO(Integer id) {
        this.id = id;
    }
}
