package bg.duosoft.nacidshared.web.service.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 14.09.2022
 * Time: 17:38
 */
@Slf4j
@Transactional
public abstract class CrudServiceBaseImpl<ID extends Serializable, DTO> implements CrudServiceBase<ID, DTO> {
    protected static final String ALL_VALUES_CACHE_NAME = "'$all-values$'";

    @Autowired
    private CacheManager cacheManager;

    protected abstract <E extends Serializable> BaseRepository<E, ID> getRepository();

    protected abstract <E extends Serializable> BaseObjectMapper<E, DTO> getMapper();

    protected abstract Validator<DTO> getValidator();

    public boolean isCacheable() {
        return false;
    }

    public boolean isLoggable() {
        return true;
    }

    /**
     * @param d
     * @return the id of the dto object which will be used inside the cache!
     */
    public String getCacheId(DTO d) {
        throw new RuntimeException("Not implemented....");
    }
    @Cacheable(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
    public List<DTO> selectAll() {
        List<Serializable> entities = getRepository().findAll();
        return getMapper().toDtoList(entities);
    }

    @Cacheable(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#objectId == null ? null : #objectId.toString()")
    public DTO selectById(ID objectId) {
        if (isCacheable()) {
            if (cacheValuesIfNotCached()) {
                return getCacheValue(objectId);
            }
        }
        Serializable e = getRepository().findById(objectId).orElse(null);
        return getMapper().toDto(e);
    }

    /**
     * @return
     *  - true if the cache was empty before the call and the elements are getting cached now
     *  - false if the values were already cached
     */
    protected boolean cacheValuesIfNotCached() {
        Cache cache = cacheManager.getCache(getCacheName());
        String cachedKey = "$cached-single-values$";
        if (cache.get(cachedKey, Boolean.class) == null) {
            synchronized (this) {
                log.debug("cache is empty. caching objects for class ..." + getClass().getName());
                List<Serializable> recs = getRepository().findAll();
                getMapper().toDtoList(recs).forEach(r -> cache.put(getCacheId(r), r));
                cache.put(cachedKey, true);
            }
            return true;
        }
        return false;
    }
    protected DTO getCacheValue(ID objectId) {
        Cache cache = cacheManager.getCache(getCacheName());
        Cache.ValueWrapper res = objectId == null ? null : cache.get(objectId.toString());
        return res == null ? null : (DTO) res.get();
    }
    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
    @LogObjectChange(id = "#result.id", after = "#result", condition = "#root.target.isLoggable()")
    public DTO create(DTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        Validator<DTO> validator = getValidator();
        if (validator != null) {
            BadRequestValidator.validateRequest(validator, dto, true, this);
        }
        beforeCreate(dto);
        beforeCreateOrUpdate(dto);
        Serializable e = getMapper().toEntity(dto);
        e = getRepository().save(e);
        return getMapper().toDto(e);
    }
    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#root.target.getCacheId(#dto)"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
            }
    )
    @LogObjectChange(id = "#dto.id", before = "#root.target.selectById(#dto.id)", after = "#result", condition = "#root.target.isLoggable()")
    public DTO update(DTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        Validator<DTO> validator = getValidator();
        if (validator != null) {
            BadRequestValidator.validateRequest(validator, dto, false, this);
        }
        beforeUpdate(dto);
        beforeCreateOrUpdate(dto);
        Serializable e = getRepository().save(getMapper().toEntity(dto));
        return getMapper().toDto(e);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#objectId == null ? null : #objectId.toString()"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
            }
    )
    @LogObjectChange(id = "#objectId", before = "#root.target.selectById(#objectId)", condition = "#root.target.isLoggable()")
    public void delete(ID objectId) {
        Serializable e = getRepository().findById(objectId).orElse(null);
        if (Objects.isNull(e)) {
            throw new ResourceNotFoundException();
        }
        beforeDeleteById(objectId);
        getRepository().delete(e);
    }
    public String getCacheName() {
        return getClass().getSimpleName();
    }

    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", allEntries = true)
    public void deleteAll() {
        getRepository().deleteAll();
    }


    /**
     * executed before calling the update method
     * @param dto
     */
    protected void beforeUpdate(DTO dto) {

    }

    /**
     * executed before calling the save method
     * @param dto
     */
    protected void beforeCreate(DTO dto) {

    }

    /**
     * executed before calling the create or update method.
     * @param dto
     */
    protected void beforeCreateOrUpdate(DTO dto) {

    }
    protected void beforeDeleteById(ID objectId) {

    }
}
