package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SecondaryProfessionalQualificationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.SecondaryProfessionalQualificationEntity;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.SecondaryProfessionalQualificationValidator;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionalQualificationFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SecondaryProfessionalQualificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondaryProfessionalQualificationService extends NomenclatureServiceBase<Integer, SecondaryProfessionalQualificationDTO, SecondaryProfessionalQualificationFilterDTO> {

    private final SecondaryProfessionalQualificationMapper mapper;
    private final SecondaryProfessionalQualificationRepository repository;
    private final SecondaryProfessionalQualificationValidator validator;

    @Override
    protected SecondaryProfessionalQualificationRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected SecondaryProfessionalQualificationMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<Integer, SecondaryProfessionalQualificationDTO, SecondaryProfessionalQualificationFilterDTO> getValidator() {
        return validator;
    }

    public List<String> selectSecondaryProfQualificationNames(SecondaryProfessionalQualificationFilterDTO filter) {
        List<SecondaryProfessionalQualificationEntity> secondaryProfessionalQualifications = repository.searchRecords(filter);
        if (!CollectionUtils.isEmpty(secondaryProfessionalQualifications)) {
            return secondaryProfessionalQualifications.stream().map(SecondaryProfessionalQualificationEntity::getName).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
