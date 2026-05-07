package bg.duosoft.nacid.backoffice.rudi.be.config.services;

import bg.duosoft.nacidservicesclient.config.ServicesFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServicesFeignClientConfig.class)
public class ServicesClientConfig {
}
