package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 18:18
 */
@Component
@RequiredArgsConstructor
public class DocTypeValidator extends BaseNomenclatureValidator<Integer, DocTypeDTO, DocTypeFilterDTO> {
}
