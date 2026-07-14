package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.DictionaryRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.DictionaryValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DictionaryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DictionaryFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DictionaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DictionaryService extends NomenclatureServiceBase<String, DictionaryDTO, DictionaryFilterDTO> {
    private final DictionaryMapper dictionaryMapper;
    private final DictionaryValidator dictionaryValidator;
    private final DictionaryRepository dictionaryRepository;

    @Override
    protected DictionaryRepository getNomenclaturesRepository() {
        return dictionaryRepository;
    }

    @Override
    protected DictionaryMapper getNomenclaturesMapper() {
        return dictionaryMapper;
    }

    @Override
    protected DictionaryValidator getValidator() {
        return dictionaryValidator;
    }
}
