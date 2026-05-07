package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBaseImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 14:06
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProfGroupDTO extends NomenclatureBaseImpl<Integer> {

    private ReferenceDataDTO educationArea;
}
