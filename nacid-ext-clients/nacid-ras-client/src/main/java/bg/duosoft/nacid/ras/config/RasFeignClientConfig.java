package bg.duosoft.nacid.ras.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacid.ras.client")
@Slf4j
public class RasFeignClientConfig {

}
