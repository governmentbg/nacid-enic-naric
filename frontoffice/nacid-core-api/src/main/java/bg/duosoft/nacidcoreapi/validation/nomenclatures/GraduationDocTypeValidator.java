package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 11:05
 */
@Component
@RequiredArgsConstructor
public class GraduationDocTypeValidator  extends BaseNomenclatureValidator<Integer, GraduationDocTypeDTO, GraduationDocTypeFilterDTO> {
}
