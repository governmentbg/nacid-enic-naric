package bg.duosoft.nacid.backoffice.rudi.be.config.services;

import bg.duosoft.nacid.ras.config.RasFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RasFeignClientConfig.class)
public class RasClientConfig {
}
