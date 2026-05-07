package bg.duosoft.nacidshareddata.config.security;

import bg.duosoft.nacidshareddata.property.KeycloakProperties;
import bg.duosoft.nacidshareddata.util.security.TokenManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:08
 */
@Configuration
@ComponentScan(basePackages = "bg.duosoft.nacidshareddata.config.webclient")
@Import(KeycloakProperties.class)
public class TokenManagerConfig {

    @Bean
    public TokenManager tokenManager(WebClient webClient, KeycloakProperties keycloakProperties){
        return new TokenManager(webClient, keycloakProperties);
    }

}
