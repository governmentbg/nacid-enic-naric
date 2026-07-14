package bg.duosoft.nacid.opendata.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "bg.duosoft.nacid.opendata.client")
@ComponentScan("bg.duosoft.nacid.opendata.config")
@Slf4j
public class OpendataFeignClientConfig {

}
