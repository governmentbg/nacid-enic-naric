package bg.duosoft.nacid.backoffice.libserv.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacid.backoffice.libserv.client.client")
@Slf4j
public class LibservFeignClientConfig {

}
