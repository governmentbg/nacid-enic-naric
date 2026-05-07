package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgServiceTypeRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CfgServiceTypeValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgServiceTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgServiceTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgServiceTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CfgServiceTypeMapper;
import bg.duosoft.nacidshared.web.service.CacheService;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CfgServiceTypeService extends CrudServiceBaseImpl<Integer, CfgServiceTypeDTO> {
    private final CfgServiceTypeMapper cfgServiceTypeMapper;
    private final CfgServiceTypeRepository cfgServiceTypeRepository;
    private final CfgServiceTypeValidator cfgServiceTypeValidator;
    private final CacheService cacheService;
    @Override
    protected CfgServiceTypeRepository getRepository() {
        return cfgServiceTypeRepository;
    }

    @Override
    protected CfgServiceTypeMapper getMapper() {
        return cfgServiceTypeMapper;
    }
    @Override
    protected Validator<CfgServiceTypeDTO> getValidator() {
        return cfgServiceTypeValidator;
    }
    public List<CfgServiceTypeDTO> selectServiceTypeData(CfgServiceTypeFilterDTO filter) {
        List<CfgServiceTypeEntity> result = cfgServiceTypeRepository.selectServiceTypeData(filter);
        return cfgServiceTypeMapper.toDtoList(result);
    }

    public int selectCountServiceTypeData(CfgServiceTypeFilterDTO filter) {
        return cfgServiceTypeRepository.countServiceTypeData(filter);
    }

    @Cacheable(value = "CfgServiceTypeService", key = "'cfg-service-types-' + #applicationType + '-'+ #applicationSubType")
    public List<CfgServiceTypeDTO> selectByApplicationTypeAndSubType(String applicationType, String applicationSubType) {
        return cfgServiceTypeMapper.toDtoList(
                cfgServiceTypeRepository.getByApplicationTypeAndSubtype(applicationType, applicationSubType)
        );
    }

    @Override
    protected void beforeCreateOrUpdate(CfgServiceTypeDTO dto) {
        cacheService.clearCache(this.getCacheName());
    }

    @Override
    protected void beforeDeleteById(Integer id) {
        cacheService.clearCache(this.getCacheName());
    }

}
