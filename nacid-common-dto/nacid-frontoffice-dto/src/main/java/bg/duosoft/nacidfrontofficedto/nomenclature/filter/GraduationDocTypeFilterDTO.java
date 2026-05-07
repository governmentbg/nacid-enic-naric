package bg.duosoft.nacidfrontofficedto.nomenclature.filter;

import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraduationDocTypeFilterDTO extends BaseNomenclatureFilterDTO<Integer> {

    private EducationType educationType;
}
