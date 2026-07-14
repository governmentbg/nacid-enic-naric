package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.DocTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.DocTypeValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.DocTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.10.2022
 * Time: 11:09
 */
@Service
@RequiredArgsConstructor
public class DocTypeServiceImpl extends NomenclatureServiceBaseImpl<Integer, DocTypeDTO, DocTypeFilterDTO> implements DocTypeService {

    private final DocTypeRepository repository;
    private final DocTypeMapper mapper;
    private final DocTypeValidator validator;

    @Override
    protected DocTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected DocTypeMapper getMapper() {
        return mapper;
    }

    @Override
    public DocTypeValidator getValidator() {
        return validator;
    }
}
