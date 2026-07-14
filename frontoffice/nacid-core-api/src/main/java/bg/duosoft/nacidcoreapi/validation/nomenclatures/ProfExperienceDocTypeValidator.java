package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfExperienceDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:32
 */
@Component
@RequiredArgsConstructor
public class ProfExperienceDocTypeValidator  extends BaseNomenclatureValidator<String, ProfExperienceDocTypeDTO, ProfExperienceDocTypeFilterDTO> {
}
