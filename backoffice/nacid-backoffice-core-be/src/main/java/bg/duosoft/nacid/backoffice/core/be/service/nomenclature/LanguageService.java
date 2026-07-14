package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.LanguageRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.LanguageValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LanguageDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LanguageFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
/*
 * <ID> - ID
 * <D> - DTO
 * <F> - Filter
 */
public class LanguageService extends NomenclatureServiceBase<String, LanguageDTO, LanguageFilterDTO> {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;
    private final LanguageValidator validator;

    @Override
    protected LanguageRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected LanguageMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<String, LanguageDTO, LanguageFilterDTO> getValidator() {
        return validator;
    }

    public List<LanguageDTO> selectByApplicationTypeSubtype(String appType, String appSubType) {
        return mapper.toDtoList(repository.selectByApplicationTypeSubtype(appType, appSubType));
    }
}
