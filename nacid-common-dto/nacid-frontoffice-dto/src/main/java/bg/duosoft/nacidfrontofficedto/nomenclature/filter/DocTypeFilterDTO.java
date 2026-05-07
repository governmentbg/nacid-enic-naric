package bg.duosoft.nacidfrontofficedto.nomenclature.filter;

import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 17:45
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocTypeFilterDTO extends BaseNomenclatureFilterDTO<Integer> {

    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
}
