package bg.duosoft.nacidshared.web.service;

import java.util.List;

public interface CacheService {

    void clearCache();

    void clearCache(String name);

    List<String> selectCacheNames();
}
