package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CivilIdTypeRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CivilIdTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CivilIdTypeMapper;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
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
@RequiredArgsConstructor
@Transactional
/*
 * <ID> - ID
 * <D> - DTO
 * <F> - Filter
 */
public class CivilIdTypeService extends NomenclatureServiceBase<String, CivilIdTypeDTO, CivilIdTypeFilterDTO> {

    private final CivilIdTypeRepository repository;
    private final CivilIdTypeMapper mapper;
    private final CivilIdTypeValidator validator;

    @Override
    protected CivilIdTypeRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected CivilIdTypeMapper getNomenclaturesMapper() {
        return mapper;
    }

    public List<CivilIdTypeDTO> selectByLegalType(String legalType, boolean onlyActive) {
        return mapper.toDtoList(repository.getAllByLegalTypeIdAndActive(legalType, onlyActive ? 1 : null));
    }

    @Override
    protected BaseNomenclatureValidator<String, CivilIdTypeDTO, CivilIdTypeFilterDTO> getValidator() {
        return validator;
    }
}
