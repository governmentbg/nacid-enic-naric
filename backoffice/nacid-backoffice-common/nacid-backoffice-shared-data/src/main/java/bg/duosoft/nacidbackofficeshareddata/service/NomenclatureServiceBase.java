package bg.duosoft.nacidbackofficeshareddata.service;


import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.BaseNomenclatureMapper;
import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Transactional
public abstract class NomenclatureServiceBase<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends CrudServiceBaseImpl<ID, D> {
    private static final String ALL_ACTIVE_VALUES_KEY = "'$all-values-true$'";
    private static final String ALL_INACTIVE_VALUES_KEY = "'$all-values-false$'";

    protected abstract <E extends NomenclatureEntityBase<ID>> NomenclatureBaseRepository<ID, E, F> getNomenclaturesRepository();

    protected abstract <E extends NomenclatureEntityBase<ID>> BaseNomenclatureMapper<E, D> getNomenclaturesMapper();

    protected abstract BaseNomenclatureValidator<ID, D, F> getValidator();

    public List<D> searchRecords(F filter) {
        return searchRecords(filter, false);
    }

    public List<D> searchRecords(F filter, boolean hasDistinct) {
        List<NomenclatureEntityBase<ID>> result = getNomenclaturesRepository().searchRecords(filter, hasDistinct);
        return getNomenclaturesMapper().toDtoList(result);
    }

    public int getRecordsCount(F filter) {
        return getNomenclaturesRepository().getRecordsCount(filter, false);
    }

    public int getRecordsCount(F filter, boolean hasDistinct) {
        return getNomenclaturesRepository().getRecordsCount(filter, hasDistinct);
    }

    @Cacheable(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "'$all-values-' + #onlyActive + '$'")
    public List<D> selectAll(boolean onlyActive) {
        List<NomenclatureEntityBase<ID>> entities = onlyActive ? getNomenclaturesRepository().getAllByActive(1) : getNomenclaturesRepository().findAll();
        return getNomenclaturesMapper().toDtoList(entities);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#id == null ? null : #id.toString()"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_ACTIVE_VALUES_KEY),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_INACTIVE_VALUES_KEY)
            }
    )
    @LogObjectChange(id = "#id", before = "#root.target.selectById(#id)", after = "#result", condition = "#root.target.isLoggable()", operation = "'update'")
    public D toggleActivation(ID id) {
        D dto = selectById(id);

        Boolean isActive = dto.getIsActive();
        if (Objects.isNull(isActive)) {
            dto.setIsActive(false);
        }

        dto.setIsActive(!isActive);
        return update(dto);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_ACTIVE_VALUES_KEY),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_INACTIVE_VALUES_KEY)
            }
    )
    @LogObjectChange(id = "#result.id", after = "#result", condition = "#root.target.isLoggable()")
    public D create(D d) {
        return super.create(d);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#root.target.getCacheId(#d)"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_ACTIVE_VALUES_KEY),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_INACTIVE_VALUES_KEY)
            }
    )
    @LogObjectChange(id = "#d.id", before = "#root.target.selectById(#d.id)", after = "#result", condition = "#root.target.isLoggable()")
    public D update(D d) {
        return super.update(d);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#objectId == null ? null : #objectId.toString()"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_ACTIVE_VALUES_KEY),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_INACTIVE_VALUES_KEY)
            }
    )
    @LogObjectChange(id = "#objectId", before = "#root.target.selectById(#objectId)", condition = "#root.target.isLoggable()")
    public void delete(ID objectId) {
        super.delete(objectId);
    }


    @Override
    protected final <E extends Serializable> BaseRepository<E, ID> getRepository() {
        return (BaseRepository<E, ID>) getNomenclaturesRepository();
    }

    @Override
    protected final <E extends Serializable> BaseObjectMapper<E, D> getMapper() {
        return (BaseObjectMapper<E, D>) getNomenclaturesMapper();
    }

    @Override
    public boolean isCacheable() {
        return true;
    }

    @Override
    public String getCacheId(D d) {
        return d.getId() == null ? null : d.getId().toString();
    }
}
