package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgGraduationWayToApplicationTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CfgGraduationWayToAppTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationWayToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgGraduationWayToAppTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgGraduationWayToAppTypeMapper;
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
 * Date: 19.09.2022
 * Time: 17:37
 */
@Service
@RequiredArgsConstructor
public class CfgGraduationWayToApplicationTypeService {
    private final CfgGraduationWayToApplicationTypeRepository repository;
    private final ReferenceDataMapper referenceDataMapper;
    private final CfgGraduationWayToAppTypeMapper mapper;

    private final CfgGraduationWayToAppTypeValidator validator;

    public List<CfgGraduationWayToAppTypeDTO> getAll() {
        return repository.findAll().stream().map(r -> mapper.toDto(r)).collect(Collectors.toList());
    }

    public CfgGraduationWayToAppTypeDTO selectById(String gwy, String ate, String ase) {
        CfgGraduationWayToAppTypeEntity cfgGraduationWayToAppTypeEntity = repository.findById(new CfgGraduationWayToAppTypeEntityPK(gwy, ate, ase)).orElse(null);
        return mapper.toDto(cfgGraduationWayToAppTypeEntity);
    }

    public void delete(String gwy, String ate, String ase) {
        CfgGraduationWayToAppTypeEntity e = repository.findById(new CfgGraduationWayToAppTypeEntityPK(gwy, ate, ase)).orElse(null);
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
                .map(r -> r.getGraduationWay())
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

    public CfgGraduationWayToAppTypeDTO insert(CfgGraduationWayToAppTypeDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        } else {
            if (validator != null) {
                BadRequestValidator.validateRequest(validator, dto, true, this);
            }
            CfgGraduationWayToAppTypeEntity e = mapper.toEntity(dto);
            e = repository.save(e);
            return mapper.toDto(e);
        }

    }

    public List<CfgGraduationWayToAppTypeDTO> selectData(CfgGraduationWayToAppTypeFilterDTO filter) {
        List<CfgGraduationWayToAppTypeEntity> result = repository.selectData(filter);
        return mapper.toDtoList(result);
    }

    public int selectCountData(CfgGraduationWayToAppTypeFilterDTO filter) {
        return repository.countData(filter);
    }
}
