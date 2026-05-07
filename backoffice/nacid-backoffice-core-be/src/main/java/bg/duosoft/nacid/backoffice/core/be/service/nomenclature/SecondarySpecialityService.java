package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SecondarySpecialityRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.SecondarySpecialityValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondarySpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondarySpecialityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SecondarySpecialityMapper;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondarySpecialityService extends NomenclatureServiceBase<Integer, SecondarySpecialityDTO, SecondarySpecialityFilterDTO> {

    private final SecondarySpecialityRepository repository;
    private final SecondarySpecialityMapper mapper;
    private final SecondarySpecialityValidator validator;


    @Override
    protected SecondarySpecialityRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected SecondarySpecialityMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<Integer, SecondarySpecialityDTO, SecondarySpecialityFilterDTO> getValidator() {
        return validator;
    }

    public List<SecondarySpecialityDTO> selectByProfessionalQualification(Integer profQualificationId, boolean onlyActive) {
        List<SecondarySpecialityEntity> entities = onlyActive ? repository.selectByProfQualificationIdOnlyActive(profQualificationId) : repository.selectByProfQualificationId(profQualificationId);
        return mapper.toDtoList(entities);
    }

}
