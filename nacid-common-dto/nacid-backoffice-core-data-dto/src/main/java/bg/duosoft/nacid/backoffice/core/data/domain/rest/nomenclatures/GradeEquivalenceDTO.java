package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.IntegerKeyNomenclatureBase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GradeEquivalenceDTO extends IntegerKeyNomenclatureBase {
    private BigDecimal bulgarianGrade;
}
