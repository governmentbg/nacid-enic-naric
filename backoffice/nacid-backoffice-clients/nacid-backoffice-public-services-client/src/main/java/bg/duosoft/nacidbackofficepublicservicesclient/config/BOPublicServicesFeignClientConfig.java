package bg.duosoft.nacidbackofficepublicservicesclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacidbackofficepublicservicesclient.client")
@Slf4j
public class BOPublicServicesFeignClientConfig {
}
