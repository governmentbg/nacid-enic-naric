package bg.duosoft.nacidfrontofficedto.nomenclature.filter;

import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 14:22
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfGroupFilterDTO  extends BaseNomenclatureFilterDTO<Integer> {

    private String educationAreaCode;
}
