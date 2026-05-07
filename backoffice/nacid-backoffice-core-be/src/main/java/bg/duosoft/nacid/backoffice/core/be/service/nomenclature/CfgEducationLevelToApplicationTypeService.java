package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgEducationLevelToApplicationTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CfgEduLevelToAppTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgEduLevelToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgEduLevelToAppTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgEduLevelToAppTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 17:37
 */
@Service
@RequiredArgsConstructor
public class CfgEducationLevelToApplicationTypeService {
    private final CfgEducationLevelToApplicationTypeRepository repository;
    private final ReferenceDataMapper referenceDataMapper;
    private final CfgEduLevelToAppTypeMapper mapper;
    private final CfgEduLevelToAppTypeValidator validator;

    public List<CfgEduLevelToAppTypeDTO> getAll() {
        return repository.findAll().stream().map(r -> mapper.toDto(r)).collect(Collectors.toList());
    }

    public CfgEduLevelToAppTypeDTO selectById(String ell, String ate, String ase) {
        CfgEduLevelToAppTypeEntity cfgEduLevelToAppTypeEntity = repository.findById(new CfgEduLevelToAppTypeEntityPK(ell, ate, ase)).orElse(null);
        return mapper.toDto(cfgEduLevelToAppTypeEntity);
    }

    public void delete(String ell, String ate, String ase) {
        CfgEduLevelToAppTypeEntity e = repository.findById(new CfgEduLevelToAppTypeEntityPK(ell, ate, ase)).orElse(null);
        if (Objects.isNull(e)) {
            throw new ResourceNotFoundException();
        } else {
            repository.delete(e);
        }
    }

    public List<ReferenceDataDTO> selectByApplicationTypeSubtype(String applicationType, String applicationSubtype) {
        return repository
                .getByApplicationTypeAndApplicationSubtype(applicationType, applicationSubtype)
                .stream()
                .map(r -> r.getEducationLevel())
                .map(r -> referenceDataMapper.toDto(r))
                .collect(Collectors.toList());
    }

    public List<ReferenceDataDTO> selectByApplicationTypeSubtypes(String applicationType, List<String> applicationSubtypes) {
        return repository
                .getByApplicationTypeAndApplicationSubtypes(applicationType, applicationSubtypes)
                .stream()
                .map(referenceDataMapper::toDto)
                .collect(Collectors.toList());
    }

    public CfgEduLevelToAppTypeDTO insert(CfgEduLevelToAppTypeDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        } else {
            if (validator != null) {
                BadRequestValidator.validateRequest(validator, dto, true, this);
            }
            CfgEduLevelToAppTypeEntity e = mapper.toEntity(dto);
            e = repository.save(e);
            return mapper.toDto(e);
        }

    }

    public List<CfgEduLevelToAppTypeDTO> selectData(CfgEduLevelToAppTypeFilterDTO filter) {
        List<CfgEduLevelToAppTypeEntity> result = repository.selectData(filter);
        return mapper.toDtoList(result);
    }

    public int selectCountData(CfgEduLevelToAppTypeFilterDTO filter) {
        return repository.countData(filter);
    }
}
