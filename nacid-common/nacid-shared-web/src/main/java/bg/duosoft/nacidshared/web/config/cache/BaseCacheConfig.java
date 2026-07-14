package bg.duosoft.nacidshared.web.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: ggeorgiev
 * Date: 13.10.2022
 * Time: 18:00
 */
@Configuration
@EnableCaching
public class BaseCacheConfig extends CachingConfigurerSupport {
    @Autowired
    private CacheManager cacheManager;
    @Bean
    public CacheResolver crudCacheResolver() {
        return new CrudCacheResolver(cacheManager);
    }
}
