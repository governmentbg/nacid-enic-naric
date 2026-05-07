package bg.duosoft.nacid.backoffice.rudi.be.config.services;

import bg.duosoft.nacid.backoffice.core.client.config.FeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientConfig.class)
public class CoreClientConfig {
}
