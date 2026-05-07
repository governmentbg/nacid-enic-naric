package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ApplicationSubtypeRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ApplicationSubTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationSubtypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 13.07.2022
 * Time: 14:34
 */
@Service
@Transactional
/*
 * <ID> - ID
 * <D> - DTO
 * <F> - Filter
 */
@RequiredArgsConstructor
public class ApplicationSubtypeService extends NomenclatureServiceBase<String, ApplicationSubtypeDTO, ApplicationSubTypeFilterDTO> {

    private final ApplicationSubtypeRepository repository;

    private final ApplicationSubtypeMapper mapper;

    private final ApplicationSubTypeValidator validator;

    @Override
    protected ApplicationSubtypeRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected ApplicationSubtypeMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected ApplicationSubTypeValidator getValidator() {
        return validator;
    }

    public List<ApplicationSubtypeDTO> selectByApplicationType(String applicationType, boolean onlyActive) {
        return mapper.toDtoList(repository.getAllByApplicationTypeIdAndActive(applicationType, onlyActive ? 1 : null));
    }
}
