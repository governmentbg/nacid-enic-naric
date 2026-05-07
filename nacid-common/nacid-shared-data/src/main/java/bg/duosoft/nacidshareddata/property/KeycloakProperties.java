package bg.duosoft.nacidshareddata.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:16
 */
@Getter
@Component
public class KeycloakProperties {

    @Value("${keycloak.client.id:#{null}}")
    private String keycloakClientId;

    @Value("${keycloak.client.secret:#{null}}")
    private String keycloakClientSecret;

    @Value("${keycloak.provider.base-uri:#{null}}")
    private String keycloakProviderBaseUri;

    @Value("${keycloak.provider.realm.name:#{null}}")
    private String keycloakProviderRealmName;
}
