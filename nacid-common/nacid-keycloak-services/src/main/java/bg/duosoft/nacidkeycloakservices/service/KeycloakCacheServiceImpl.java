package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidkeycloakservices.enums.CacheType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakCacheServiceImpl extends BaseKeycloakService implements KeycloakCacheService {

    @Override
    public void clearCache(CacheType cacheType) {
        super.clearCache(cacheType);
    }
}
