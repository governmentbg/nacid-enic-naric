package bg.duosoft.nacid.backoffice.core.be.config.services;

import bg.duosoft.nacid.backoffice.rudi.client.config.RudiFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RudiFeignClientConfig.class)
public class RudiClientConfig {
}
