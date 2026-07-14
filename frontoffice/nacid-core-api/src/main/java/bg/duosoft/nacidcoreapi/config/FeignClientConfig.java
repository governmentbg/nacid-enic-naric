package bg.duosoft.nacidcoreapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacidcoreapi.integration")
@Slf4j
public class FeignClientConfig {

}
