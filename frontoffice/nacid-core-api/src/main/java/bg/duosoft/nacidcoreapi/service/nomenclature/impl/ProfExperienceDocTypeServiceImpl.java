package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.ProfExperienceDocTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.ProfExperienceDocTypeService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBaseImpl;
import bg.duosoft.nacidcoreapi.validation.nomenclatures.ProfExperienceDocTypeValidator;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ProfExperienceDocTypeMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfExperienceDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:29
 */
@Service
@RequiredArgsConstructor
public class ProfExperienceDocTypeServiceImpl extends NomenclatureServiceBaseImpl<String, ProfExperienceDocTypeDTO, ProfExperienceDocTypeFilterDTO> implements ProfExperienceDocTypeService {

    private final ProfExperienceDocTypeRepository repository;
    private final ProfExperienceDocTypeMapper mapper;
    private final ProfExperienceDocTypeValidator validator;

    @Override
    protected ProfExperienceDocTypeRepository getRepository() {
        return repository;
    }

    @Override
    protected ProfExperienceDocTypeMapper getMapper() {
        return mapper;
    }

    @Override
    public ProfExperienceDocTypeValidator getValidator() {
        return validator;
    }
}
