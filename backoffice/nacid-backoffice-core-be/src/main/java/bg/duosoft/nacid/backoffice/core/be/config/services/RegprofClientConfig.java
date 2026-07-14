package bg.duosoft.nacid.backoffice.core.be.config.services;

import bg.duosoft.nacid.backoffice.regprof.client.config.RegprofFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RegprofFeignClientConfig.class)
public class RegprofClientConfig {
}
