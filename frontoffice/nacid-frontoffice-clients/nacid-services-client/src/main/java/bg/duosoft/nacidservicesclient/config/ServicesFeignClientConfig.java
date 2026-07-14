package bg.duosoft.nacidservicesclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 14:52
 */
@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacidservicesclient.client")
@Slf4j
public class ServicesFeignClientConfig {
}
