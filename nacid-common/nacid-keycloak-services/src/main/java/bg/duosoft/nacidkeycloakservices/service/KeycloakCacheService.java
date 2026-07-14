package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidkeycloakservices.enums.CacheType;

public interface KeycloakCacheService {

    void clearCache(CacheType cacheType);

}
