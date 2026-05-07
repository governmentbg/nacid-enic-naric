package bg.duosoft.nacidfrontofficedto.nomenclature.filter;

import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:19
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfExperienceDocTypeFilterDTO extends BaseNomenclatureFilterDTO<String> {

    private Boolean forExperienceCalculation;
}