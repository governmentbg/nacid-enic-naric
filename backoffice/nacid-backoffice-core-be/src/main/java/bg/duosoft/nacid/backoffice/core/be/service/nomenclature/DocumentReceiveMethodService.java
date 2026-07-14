package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.DocumentReceiveMethodRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.DocumentReceiveMethodValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.DocumentReceiveMethodMapper;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 14:34
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentReceiveMethodService extends NomenclatureServiceBase<String, DocumentReceiveMethodDTO, DocumentReceiveMethodFilterDTO> {
    private final DocumentReceiveMethodRepository repository;
    private final DocumentReceiveMethodMapper mapper;
    private final DocumentReceiveMethodValidator validator;

    @Override
    protected DocumentReceiveMethodRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected DocumentReceiveMethodMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<String, DocumentReceiveMethodDTO, DocumentReceiveMethodFilterDTO> getValidator() {
        return validator;
    }

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"DocumentReceiveMethodService"})
    public DocumentReceiveMethodDTO create(DocumentReceiveMethodDTO documentReceiveMethodDTO) {
        DocumentReceiveMethodDTO result = super.create(documentReceiveMethodDTO);
        resetDefaults(result);
        return result;
    }

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"DocumentReceiveMethodService"})
    public DocumentReceiveMethodDTO update(DocumentReceiveMethodDTO documentReceiveMethodDTO) {
        DocumentReceiveMethodDTO result = super.update(documentReceiveMethodDTO);
        resetDefaults(result);
        return result;
    }

    private void resetDefaults(DocumentReceiveMethodDTO documentReceiveMethodDTO) {
        if (documentReceiveMethodDTO.getDefaultFlag()) {
            if (StringUtils.hasText(documentReceiveMethodDTO.getId())) {
                repository.resetDefaultById(documentReceiveMethodDTO.getId());
            }
        }
    }
}
