package bg.duosoft.nacidkeycloakservices.service;

import bg.duosoft.nacidkeycloakservices.enums.CacheType;
import bg.duosoft.nacidkeycloakservices.properties.KeycloakServicesPropertyAccess;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 11:10
 */
public abstract class BaseKeycloakService {

    @Autowired
    private Keycloak keycloakServiceExecutor;

    @Autowired
    private KeycloakServicesPropertyAccess keycloakServicesPropertyAccess;

    protected void clearCache(CacheType cacheType) {
        if (Objects.nonNull(cacheType)) {
            switch (cacheType) {
                case USER:
                    realmResource().clearUserCache();
                    break;
                case REALM:
                    realmResource().clearRealmCache();
                    break;
                case KEYS:
                    realmResource().clearKeysCache();
                    break;
                case ALL:
                    realmResource().clearUserCache();
                    realmResource().clearRealmCache();
                    realmResource().clearKeysCache();
                    break;
            }
        }
    }

    protected RealmResource realmResource() {
        return keycloakServiceExecutor.realm(keycloakServicesPropertyAccess.getKeycloakProviderRealmName());
    }

    protected String realm(){
        return keycloakServicesPropertyAccess.realm();
    }
}
