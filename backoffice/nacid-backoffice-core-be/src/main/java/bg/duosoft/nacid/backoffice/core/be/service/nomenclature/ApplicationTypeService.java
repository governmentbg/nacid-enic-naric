package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ApplicationTypeRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ApplicationTypeValidator;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.BaseNomenclatureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 14:34
 */
@Service
@Transactional
@RequiredArgsConstructor
/*
 * <ID> - ID
 * <D> - DTO
 * <F> - Filter
 */

public class ApplicationTypeService extends NomenclatureServiceBase<String, ApplicationTypeDTO, ApplicationTypeFilterDTO> {

    private final ApplicationTypeRepository repository;
    private final ApplicationTypeMapper mapper;
    private final ApplicationTypeValidator validator;

    @Override
    protected ApplicationTypeRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected BaseNomenclatureMapper<ApplicationTypeEntity, ApplicationTypeDTO> getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<String, ApplicationTypeDTO, ApplicationTypeFilterDTO> getValidator() {
        return validator;
    }
}
