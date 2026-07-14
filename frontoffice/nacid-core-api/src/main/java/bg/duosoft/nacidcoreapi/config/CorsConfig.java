package bg.duosoft.nacidcoreapi.config;

import bg.duosoft.nacidshared.web.config.CorsOriginsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CorsOriginsConfig.class})
public class CorsConfig {
}
