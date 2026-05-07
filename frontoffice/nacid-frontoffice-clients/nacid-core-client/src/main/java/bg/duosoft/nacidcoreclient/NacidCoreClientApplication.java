package bg.duosoft.nacidcoreclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NacidCoreClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacidCoreClientApplication.class, args);
    }

}
