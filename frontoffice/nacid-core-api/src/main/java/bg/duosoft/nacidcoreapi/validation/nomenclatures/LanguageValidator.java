package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:55
 */
@Component
@RequiredArgsConstructor
public class LanguageValidator extends BaseNomenclatureValidator<String, LanguageDTO, LanguageFilterDTO> {
}
