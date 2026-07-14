package bg.duosoft.nacid.backoffice.rudi.be.config;

import bg.duosoft.nacidshared.web.config.ajp.AjpConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AjpConfiguration.class})
public class AjpConfig {
}