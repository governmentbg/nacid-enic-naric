package bg.duosoft.nacidservicesbe.config;

import bg.duosoft.nacidshared.web.config.ajp.AjpConfiguration;
import bg.duosoft.nacidshared.web.property.AjpProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AjpProperties.class, AjpConfiguration.class})
public class AjpConfig {
}