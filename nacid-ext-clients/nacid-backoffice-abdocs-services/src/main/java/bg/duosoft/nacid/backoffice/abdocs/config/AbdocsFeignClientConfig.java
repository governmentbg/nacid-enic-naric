package bg.duosoft.nacid.backoffice.abdocs.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacid.backoffice.abdocs.client")
public class AbdocsFeignClientConfig {

}
