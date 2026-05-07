package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 14:59
 */
@Component
@RequiredArgsConstructor
public class ProfGroupValidator extends BaseNomenclatureValidator<Integer, ProfGroupDTO, ProfGroupFilterDTO>  {
}
