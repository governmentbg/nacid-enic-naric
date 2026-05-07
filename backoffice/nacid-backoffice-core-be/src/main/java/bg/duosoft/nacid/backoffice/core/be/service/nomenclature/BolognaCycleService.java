package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.BolognaCycleRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.BolognaCycleValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BolognaCycleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.BolognaCycleFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.BolognaCycleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BolognaCycleService extends NomenclatureServiceBase<Integer, BolognaCycleDTO, BolognaCycleFilterDTO> {

    private final BolognaCycleMapper mapper;
    private final BolognaCycleValidator validator;
    private final BolognaCycleRepository repository;

    @Override
    protected BolognaCycleRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected BolognaCycleMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BolognaCycleValidator getValidator() {
        return validator;
    }
}
