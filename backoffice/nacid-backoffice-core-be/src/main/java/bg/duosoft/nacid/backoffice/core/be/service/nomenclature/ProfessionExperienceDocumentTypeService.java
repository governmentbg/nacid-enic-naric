package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ProfessionExperienceDocumentTypeRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ProfessionExperienceDocumentTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfessionExperienceDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ProfessionExperienceDocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ProfessionExperienceDocumentTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ggeorgiev
 * Date: 13.09.2022
 * Time: 13:32
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProfessionExperienceDocumentTypeService extends NomenclatureServiceBase<String, ProfessionExperienceDocumentTypeDTO, ProfessionExperienceDocumentTypeFilterDTO> {
    private final ProfessionExperienceDocumentTypeMapper mapper;
    private final ProfessionExperienceDocumentTypeValidator validator;
    private final ProfessionExperienceDocumentTypeRepository repository;

    @Override
    protected ProfessionExperienceDocumentTypeRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected ProfessionExperienceDocumentTypeMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected ProfessionExperienceDocumentTypeValidator getValidator() {
        return validator;
    }

}
