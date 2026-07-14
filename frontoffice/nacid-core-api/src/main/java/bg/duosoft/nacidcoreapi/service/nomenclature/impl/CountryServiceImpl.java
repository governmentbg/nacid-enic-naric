package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CountryRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CountryService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.CountryValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CountryServiceImpl extends NomenclatureServiceBaseImpl<String, CountryDTO, CountryFilterDTO> implements CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;
    private final CountryValidator validator;

    @Override
    protected CountryRepository getRepository() {
        return repository;
    }

    @Override
    protected CountryMapper getMapper() {
        return mapper;
    }

    @Override
    public CountryValidator getValidator() {
        return validator;
    }
}
