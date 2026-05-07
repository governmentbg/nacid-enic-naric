package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SettlementRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.SettlementValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SettlementDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SettlementFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementService extends NomenclatureServiceBase<String, SettlementDTO, SettlementFilterDTO> {

    private final SettlementRepository repository;
    private final SettlementMapper mapper;
    private final SettlementValidator validator;

    @Override
    protected SettlementRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected SettlementMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected SettlementValidator getValidator() {
        return validator;
    }

}
