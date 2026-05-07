package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CountryRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CountryValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
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
public class CountryService extends NomenclatureServiceBase<String, CountryDTO, CountryFilterDTO> {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CountryValidator validator;

    @Override
    protected CountryRepository getNomenclaturesRepository() {
        return countryRepository;
    }

    @Override
    protected CountryMapper getNomenclaturesMapper() {
        return countryMapper;
    }

    @Override
    protected BaseNomenclatureValidator<String, CountryDTO, CountryFilterDTO> getValidator() {
        return validator;
    }
}
