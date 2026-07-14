package bg.duosoft.nacid.backoffice.core.be.config.services;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacidcoreclient.client.fileStore")
public class FoCoreClientConfig {
}
