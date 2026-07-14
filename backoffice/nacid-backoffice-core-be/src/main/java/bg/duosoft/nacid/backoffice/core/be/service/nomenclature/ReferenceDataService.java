package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;


import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ReferenceDataDomainRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ReferenceDataRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ReferenceDataValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataDomainEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDomainDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ReferenceDataFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataDomainMapper;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReferenceDataService {

    private final ReferenceDataRepository repository;
    private final ReferenceDataDomainRepository referenceDataDomainRepository;
    private final ReferenceDataMapper mapper;

    private final ReferenceDataDomainMapper referenceDataDomainMapper;

    private final ReferenceDataValidator referenceDataValidator;

    private final CacheManager cacheManager;

    public List<ReferenceDataDTO> selectAll(String domain, boolean onlyActive) {
        List<ReferenceDataEntity> entities = onlyActive ? repository.getAllByPkDomainAndActiveOrderByIndexAscNameAsc(domain, 1) : repository.getAllByPkDomainOrderByIndexAscNameAsc(domain);
        return mapper.toDtoList(entities);
    }

    @Cacheable(value = "referenceData", key = "new bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK(#domain, #id)")
    public ReferenceDataDTO selectById(String domain, String id) {
        Cache cache = cacheManager.getCache("referenceData");
        ReferenceDataEntityPK key = new ReferenceDataEntityPK(domain, id);
        if (cache.get("cached", Boolean.class) == null) {
            synchronized (this) {
                log.debug("cache is empty. caching reference data...");
                List<ReferenceDataEntity> all = repository.findAll();
                mapper.toDtoList(all).forEach(r -> cache.put(new ReferenceDataEntityPK(r.getDomain(), r.getId()), r));
                cache.put("cached", true);
                return cache.get(key, ReferenceDataDTO.class);
            }
        }
        //this code will be executed if any of the records is evicted by the delete / update / methods
        Optional<ReferenceDataEntity> eOpt = repository.findById(new ReferenceDataEntityPK(domain, id));
        return eOpt.map(mapper::toDto).orElse(null);
    }

    @CacheEvict(value = "referenceData", key = "new bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK(#dto.domain, #dto.id)")
    @LogObjectChange(id = "#dto.domain + '/' + #dto.id", after = "#result", condition = "#root.target.isLoggable()")
    public ReferenceDataDTO create(ReferenceDataDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(referenceDataValidator, dto, true, this);
        ReferenceDataEntity e = repository.save(mapper.toEntity(dto));
        return mapper.toDto(e);
    }

    @CacheEvict(value = "referenceData", key = "new bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK(#dto.domain, #dto.id)")
    @LogObjectChange(id = "#dto.domain + '/' + #dto.id", before = "#root.target.selectById(#dto.domain, #dto.id)", after = "#result", condition = "#root.target.isLoggable()")
    public ReferenceDataDTO update(ReferenceDataDTO dto) {
        log.debug("Inside update...");
        if (Objects.isNull(dto) || Objects.isNull(dto.getId())) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(referenceDataValidator, dto, false, this);
        ReferenceDataEntity e = repository.save(mapper.toEntity(dto));
        return mapper.toDto(e);
    }

    @CacheEvict(value = "referenceData", key = "new bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK(#domain, #id)")
    @LogObjectChange(id = "#domain + '/' + #id", before = "#root.target.selectById(#domain, #id)", condition = "#root.target.isLoggable()")
    public void delete(String domain, String id) {

        ReferenceDataEntity e = repository.findById(new ReferenceDataEntityPK(domain, id)).orElse(null);

        if (Objects.isNull(e)) {
            throw new ResourceNotFoundException();
        }

        repository.delete(e);
    }

    public boolean isLoggable() {
        return true;
    }
    @LogObjectChange(id = "#domain + '/' + #id", before = "#root.target.selectById(#domain, #id)", after = "#result", condition = "#root.target.isLoggable()", operation = "'update'")
    @CacheEvict(value = "referenceData", key = "new bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK(#domain, #id)")
    public ReferenceDataDTO toggleActivation(String domain, String id) {
        ReferenceDataDTO dto = selectById(domain, id);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Cannot find reference data object with DOMAIN = " + domain + " and ID = " + id);
        }

        Boolean isActive = dto.getIsActive();
        if (Objects.isNull(isActive)) {
            dto.setIsActive(false);
        }

        dto.setIsActive(!isActive);
        return update(dto);
    }

    public List<ReferenceDataDTO> selectReferenceData(ReferenceDataFilterDTO filter) {
        List<ReferenceDataEntity> result = repository.selectReferenceData(filter);
        return mapper.toDtoList(result);
    }

    public int selectReferenceDataCount(ReferenceDataFilterDTO filter) {
        return repository.selectReferenceDataCount(filter);
    }

    public List<ReferenceDataDomainDTO> selectDomains() {
        List<ReferenceDataDomainEntity> res = referenceDataDomainRepository.findAll();
        return referenceDataDomainMapper.toDtoList(res);
    }

    public ReferenceDataDomainDTO create(ReferenceDataDomainDTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
//        BadRequestValidator.validateRequest(referenceDataValidator, dto, true, this);
        ReferenceDataDomainEntity e = referenceDataDomainRepository.save(referenceDataDomainMapper.toEntity(dto));
        return referenceDataDomainMapper.toDto(e);
    }

    public List<ReferenceDataDTO> selectByDomain(String domain) {
        List<ReferenceDataEntity> entities = repository.getAllByDomain(domain);
        return mapper.toDtoList(entities);
    }

}
