package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.SettlementRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.SettlementService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.SettlementValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.SettlementMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.SettlementFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementServiceImpl extends NomenclatureServiceBaseImpl<String, SettlementDTO, SettlementFilterDTO> implements SettlementService {

    private final SettlementRepository repository;
    private final SettlementMapper mapper;
    private final SettlementValidator validator;

    @Override
    protected SettlementRepository getRepository() {
        return repository;
    }

    @Override
    protected SettlementMapper getMapper() {
        return mapper;
    }

    @Override
    public SettlementValidator getValidator() {
        return validator;
    }

}
