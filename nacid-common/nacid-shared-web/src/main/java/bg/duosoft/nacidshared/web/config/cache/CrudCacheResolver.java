package bg.duosoft.nacidshared.web.config.cache;

import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.util.Arrays;
import java.util.Collection;

/**
 * User: ggeorgiev
 * Date: 17.10.2022
 * Time: 14:06
 */
class CrudCacheResolver extends SimpleCacheResolver {
    public CrudCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        if (context.getTarget() instanceof CrudServiceBaseImpl c) {
            return Arrays.asList(c.getCacheName());
        } else {
            throw new RuntimeException("CrudCacheResolver should be used for classes that extend CrudServiceBaseImpl");
        }
    }
}
