package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.ProfGroupRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.ProfGroupService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.ProfGroupValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ProfGroupMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 15:07
 */
@Service
@RequiredArgsConstructor
public class ProfGroupServiceImpl extends NomenclatureServiceBaseImpl<Integer, ProfGroupDTO, ProfGroupFilterDTO> implements ProfGroupService {

    private final ProfGroupRepository repository;
    private final ProfGroupMapper mapper;
    private final ProfGroupValidator validator;

    @Override
    protected ProfGroupRepository getRepository() {
        return repository;
    }

    @Override
    protected ProfGroupMapper getMapper() {
        return mapper;
    }

    @Override
    public ProfGroupValidator getValidator() {
        return validator;
    }
}
