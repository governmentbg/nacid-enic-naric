package bg.duosoft.nacidservicesbe.config;

import bg.duosoft.nacidkeycloakservices.config.KeycloakDatabaseConfig;
import bg.duosoft.nacidkeycloakservices.config.KeycloakRestServicesConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({KeycloakRestServicesConfig.class, KeycloakDatabaseConfig.class})
public class KeycloakServicesConfig {
}
