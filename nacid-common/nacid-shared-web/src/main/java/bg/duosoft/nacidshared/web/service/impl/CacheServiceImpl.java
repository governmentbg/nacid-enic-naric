package bg.duosoft.nacidshared.web.service.impl;

import bg.duosoft.nacidshared.web.exception.CacheNotClearedException;
import bg.duosoft.nacidshared.web.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    @Override
    public void clearCache() {
        List<String> cacheNames = selectCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (Objects.nonNull(cache)) {
                    cache.clear();
                }
            }
        }
    }

    @Override
    public void clearCache(String name) {
        Cache cache = cacheManager.getCache(name);
        if (Objects.nonNull(cache)) {
            cache.clear();
            return;
        }
        throw new CacheNotClearedException("=====[CACHE LOG]===== Cannot clear the cache with name " + name);
    }

    @Override
    public List<String> selectCacheNames() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        if (CollectionUtils.isEmpty(cacheNames)) {
            return null;
        }
        return cacheNames.stream().sorted().collect(Collectors.toList());
    }
}
