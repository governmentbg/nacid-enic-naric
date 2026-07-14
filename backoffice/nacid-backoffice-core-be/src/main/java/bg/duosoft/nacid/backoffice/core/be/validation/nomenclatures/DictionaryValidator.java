package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DictionaryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DictionaryFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DictionaryValidator extends BaseStringKeyNomenclatureValidator<DictionaryDTO, DictionaryFilterDTO>  {
}
