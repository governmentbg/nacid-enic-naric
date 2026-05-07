package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.LegalReasonRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.LegalReasonValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.LegalReasonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LegalReasonService extends NomenclatureServiceBase<Integer, LegalReasonDTO, LegalReasonFilterDTO> {

    private final LegalReasonMapper mapper;
    private final LegalReasonValidator validator;
    private final LegalReasonRepository repository;

    @Override
    protected LegalReasonRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected LegalReasonMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected LegalReasonValidator getValidator() {
        return validator;
    }
    @Cacheable(value = "LegalReasonService", key = "'status-code-' + #statusCode + #onlyActive")
    public List<LegalReasonDTO> selectByStatusCode(String statusCode, boolean onlyActive) {
        return mapper.toDtoList(repository.selectByStatusCode(statusCode, onlyActive));
    }

    public List<LegalReasonDTO> selectByStatusApplicationTypeSubtype(Integer selectedLegalReasonId, String statusCode, String applicationType, String applicationSubtype, boolean onlyActive) {
        return mapper.toDtoList(repository.selectByStatusApplicationTypeSubtype(selectedLegalReasonId, statusCode, applicationType, applicationSubtype, onlyActive));
    }
}
