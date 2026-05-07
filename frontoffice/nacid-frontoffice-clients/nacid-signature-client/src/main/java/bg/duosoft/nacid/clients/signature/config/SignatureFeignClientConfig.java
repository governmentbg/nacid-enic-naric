package bg.duosoft.nacid.clients.signature.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * User: ggeorgiev
 * Date: 30.01.2025
 * Time: 13:58
 */
@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacid.clients.signature.client")
public class SignatureFeignClientConfig {
}
