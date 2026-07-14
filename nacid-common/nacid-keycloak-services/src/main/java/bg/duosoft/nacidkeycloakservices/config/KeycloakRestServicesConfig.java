package bg.duosoft.nacidkeycloakservices.config;

import bg.duosoft.nacidkeycloakservices.properties.KeycloakServicesPropertyAccess;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = "bg.duosoft.nacidkeycloakservices.mapper")
public class KeycloakRestServicesConfig {

    private final KeycloakServicesPropertyAccess keycloakServicesPropertyAccess;

    @Bean
    public Keycloak keycloakServiceExecutor() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServicesPropertyAccess.getKeycloakProviderBaseUri())
                .realm(keycloakServicesPropertyAccess.getKeycloakProviderRealmName())
                .username(keycloakServicesPropertyAccess.getKeycloakProviderRealmAdminUser())
                .password(keycloakServicesPropertyAccess.getKeycloakProviderRealmAdminPassword())
                .clientId(keycloakServicesPropertyAccess.getKeycloakClientId())
                .clientSecret(keycloakServicesPropertyAccess.getKeycloakClientSecret())
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(20).build())
                .build();
    }
}
