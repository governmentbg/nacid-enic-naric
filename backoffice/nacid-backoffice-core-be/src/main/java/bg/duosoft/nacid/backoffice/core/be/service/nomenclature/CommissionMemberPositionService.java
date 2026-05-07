package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CommissionMemberPositionRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CommissionMemberPositionValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CommissionMemberPositionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CommissionMemberPositionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommissionMemberPositionService extends NomenclatureServiceBase<String, CommissionMemberPositionDTO, CommissionMemberPositionFilterDTO> {

    private final CommissionMemberPositionMapper mapper;
    private final CommissionMemberPositionValidator validator;
    private final CommissionMemberPositionRepository repository;

    @Override
    protected CommissionMemberPositionRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected CommissionMemberPositionMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected CommissionMemberPositionValidator getValidator() {
        return validator;
    }
}
