package bg.duosoft.nacid.backoffice.rudi.be.config;

import bg.duosoft.nacidshared.web.config.CorsOriginsConfig;
import bg.duosoft.nacidshared.web.property.CorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CorsProperties.class, CorsOriginsConfig.class})
public class CorsConfig {
}