package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.LanguageRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.LanguageService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.LanguageValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.LanguageMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:54
 */
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl extends NomenclatureServiceBaseImpl<String, LanguageDTO, LanguageFilterDTO> implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;
    private final LanguageValidator validator;

    @Override
    protected LanguageRepository getRepository() {
        return repository;
    }

    @Override
    protected LanguageMapper getMapper() {
        return mapper;
    }

    @Override
    public LanguageValidator getValidator() {
        return validator;
    }
}
