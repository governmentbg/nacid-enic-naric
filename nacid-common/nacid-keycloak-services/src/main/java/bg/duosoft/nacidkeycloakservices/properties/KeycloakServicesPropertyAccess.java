package bg.duosoft.nacidkeycloakservices.properties;

import bg.duosoft.nacidkeycloakservices.exception.RealmConfigurationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Getter
@Component
public class KeycloakServicesPropertyAccess {

    @Value("${keycloak.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String kcHibernateDialect;

    @Value("${keycloak.hibernate.show-sql:#{false}}")
    private Boolean kcHibernateShowSql;

    @Value("${keycloak.hibernate.dynamic-update:#{true}}")
    private Boolean kcHibernateDynamicUpdate;

    @Value("${keycloak.hibernate.format-sql:#{false}}")
    private Boolean kcHibernateFormatSql;

    @Value("${keycloak.client.id}")
    private String keycloakClientId;

    @Value("${keycloak.client.secret}")
    private String keycloakClientSecret;

    @Value("${keycloak.provider.base-uri}")
    private String keycloakProviderBaseUri;

    @Value("${keycloak.provider.realm.name}")
    private String keycloakProviderRealmName;

    @Value("${keycloak.provider.realm.admin-user}")
    private String keycloakProviderRealmAdminUser;

    @Value("${keycloak.provider.realm.admin-password}")
    private String keycloakProviderRealmAdminPassword;


    public String realm() {
        String realm = getKeycloakProviderRealmName();
        if (!StringUtils.hasText(realm)) {
            log.error("=====KEYCLOAK SERVICES===== Keycloak realm is not configured in yml file !");
            throw new RealmConfigurationException();
        }
        return realm;
    }

}
