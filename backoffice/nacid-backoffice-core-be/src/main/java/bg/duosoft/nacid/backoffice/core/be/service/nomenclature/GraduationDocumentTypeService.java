package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.GraduationDocumentTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.GraduationDocumentTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationDocumentTypeConfigEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.GraduationDocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationDocumentTypeConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.GraduationDocumentTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.GraduationDocumentTypeMapper;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GraduationDocumentTypeService extends NomenclatureServiceBase<Integer, GraduationDocumentTypeDTO, GraduationDocumentTypeFilterDTO> {

    private final GraduationDocumentTypeMapper mapper;
    private final GraduationDocumentTypeValidator validator;
    private final GraduationDocumentTypeRepository repository;

    @Override
    protected GraduationDocumentTypeRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected GraduationDocumentTypeMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected GraduationDocumentTypeValidator getValidator() {
        return validator;
    }

    public List<GraduationDocumentTypeDTO> selectByCountryAndEducation(String countryCode, String educationType) {
        return mapper.toDtoList(repository.selectByCountryAndEducation(countryCode, educationType));
    }

    public List<GraduationDocumentTypeDTO> selectByCountry(String countryCode) {
        return mapper.toDtoList(repository.selectByCountry(countryCode));
    }

    @Override
    public GraduationDocumentTypeDTO create(GraduationDocumentTypeDTO graduationDocumentTypeDTO) {
        List<GraduationDocumentTypeEntity> existingGraduationDocumentTypes = repository.selectByName(graduationDocumentTypeDTO.getName());
        if (CollectionUtils.isEmpty(existingGraduationDocumentTypes)) {
            return super.create(graduationDocumentTypeDTO);
        } else {
            GraduationDocumentTypeEntity graduationDocumentTypeEntity = existingGraduationDocumentTypes.get(0);
            CfgGraduationDocumentTypeConfigDTO cfgGraduationDocumentTypeConfigDTO = graduationDocumentTypeDTO.getConfigs().get(0);
            List<CfgGraduationDocumentTypeConfigEntity> configs = graduationDocumentTypeEntity.getConfigs();
            if (configs.stream().anyMatch(x -> x.getCountry().getId().equals(cfgGraduationDocumentTypeConfigDTO.getCountry().getId()) && x.getEducationType().getPk().getId().equals(cfgGraduationDocumentTypeConfigDTO.getEducationType().getId()))) {
                return mapper.toDto(graduationDocumentTypeEntity);
            } else {
                GraduationDocumentTypeDTO dto = mapper.toDto(graduationDocumentTypeEntity);
                CfgGraduationDocumentTypeConfigDTO configDto = graduationDocumentTypeDTO.getConfigs().get(0);
                dto.getConfigs().add(new CfgGraduationDocumentTypeConfigDTO(configDto.getCountry(), configDto.getEducationType()));
                return update(dto);
            }
        }
    }
}
