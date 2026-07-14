package bg.duosoft.nacid.backoffice.core.be.config.services;

import bg.duosoft.nacid.backoffice.libserv.client.config.LibservFeignClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(LibservFeignClientConfig.class)
public class LibservClientConfig {
}
