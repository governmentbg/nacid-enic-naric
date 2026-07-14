package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ProfGroupRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ProfGroupValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfGroupFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ProfGroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProfGroupService extends NomenclatureServiceBase<Integer, ProfGroupDTO, ProfGroupFilterDTO> {

    private final ProfGroupMapper mapper;
    private final ProfGroupValidator validator;
    private final ProfGroupRepository repository;

    @Override
    protected ProfGroupRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected ProfGroupMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected ProfGroupValidator getValidator() {
        return validator;
    }
}
