package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GradeEquivalenceDTO extends NomenclatureBaseImpl<Integer> {
    private BigDecimal bulgarianGrade;
}
