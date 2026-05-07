package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SecondaryProfessionGroupRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.SecondaryProfessionGroupValidator;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SecondaryProfessionGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondaryProfessionGroupService extends NomenclatureServiceBase<Integer, SecondaryProfessionGroupDTO, SecondaryProfessionGroupFilterDTO> {

    private final SecondaryProfessionGroupMapper mapper;

    private final SecondaryProfessionGroupRepository repository;

    private final SecondaryProfessionGroupValidator validator;

    @Override
    protected SecondaryProfessionGroupRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected SecondaryProfessionGroupMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<Integer, SecondaryProfessionGroupDTO, SecondaryProfessionGroupFilterDTO> getValidator() {
        return validator;
    }
}
