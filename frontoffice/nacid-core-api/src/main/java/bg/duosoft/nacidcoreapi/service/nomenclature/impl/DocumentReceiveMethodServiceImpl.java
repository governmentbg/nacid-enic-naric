package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.DocumentReceiveMethodRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocumentReceiveMethodService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.DocumentReceiveMethodValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveMethodMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocumentReceiveMethodDataFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentReceiveMethodServiceImpl extends NomenclatureServiceBaseImpl<String, DocumentReceiveMethodDTO, DocumentReceiveMethodDataFilterDTO> implements DocumentReceiveMethodService {

    private final DocumentReceiveMethodRepository repository;
    private final DocumentReceiveMethodMapper mapper;
    private final DocumentReceiveMethodValidator validator;

    @Override
    protected DocumentReceiveMethodRepository getRepository() {
        return repository;
    }

    @Override
    protected DocumentReceiveMethodMapper getMapper() {
        return mapper;
    }

    @Override
    public DocumentReceiveMethodValidator getValidator() {
        return validator;
    }
}
