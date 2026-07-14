package bg.duosoft.nacidminioservices.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"bg.duosoft.nacidminioservices.property","bg.duosoft.nacidminioservices.validation","bg.duosoft.nacidminioservices.service"})
public class MinioScanConfig {
}
