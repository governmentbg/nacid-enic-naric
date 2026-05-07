package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgRecognitionCategoryToApplicationTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CfgRecognitionCategoryToAppTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgRecognitionCategoryToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgRecognitionCategoryToAppTypeEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgRecognitionCategoryToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgRecognitionCategoryToAppTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgRecognitionCategoryToAppTypeMapper;
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
 * Date: 30.05.2022
 */
@Service
@RequiredArgsConstructor
public class CfgRecognitionCategoryToApplicationTypeService {
    private final CfgRecognitionCategoryToApplicationTypeRepository repository;
    private final ReferenceDataMapper referenceDataMapper;
    private final CfgRecognitionCategoryToAppTypeMapper mapper;
    private final CfgRecognitionCategoryToAppTypeValidator validator;

    public List<CfgRecognitionCategoryToAppTypeDTO> getAll() {
        return repository.findAll().stream().map(r -> mapper.toDto(r)).collect(Collectors.toList());
    }

    public CfgRecognitionCategoryToAppTypeDTO selectById(String rcyCode, String ateCode, String aseCode) {
        CfgRecognitionCategoryToAppTypeEntity cfgRecognitionCategoryToAppTypeEntity = repository.findById(new CfgRecognitionCategoryToAppTypeEntityPK(rcyCode, ateCode, aseCode)).orElse(null);
        return mapper.toDto(cfgRecognitionCategoryToAppTypeEntity);
    }

    public void delete(String rcyCode, String ateCode, String aseCode) {
        CfgRecognitionCategoryToAppTypeEntity e = repository.findById(new CfgRecognitionCategoryToAppTypeEntityPK(rcyCode, ateCode, aseCode)).orElse(null);
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
                .map(r -> r.getRecognitionCategory())
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

    public CfgRecognitionCategoryToAppTypeDTO insert(CfgRecognitionCategoryToAppTypeDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        } else {
            if (validator != null) {
                BadRequestValidator.validateRequest(validator, dto, true, this);
            }
            CfgRecognitionCategoryToAppTypeEntity e = mapper.toEntity(dto);
            e = repository.save(e);
            return mapper.toDto(e);
        }

    }

    public List<CfgRecognitionCategoryToAppTypeDTO> selectData(CfgRecognitionCategoryToAppTypeFilterDTO filter) {
        List<CfgRecognitionCategoryToAppTypeEntity> result = repository.selectData(filter);
        return mapper.toDtoList(result);
    }

    public int selectCountData(CfgRecognitionCategoryToAppTypeFilterDTO filter) {
        return repository.countData(filter);
    }
}
