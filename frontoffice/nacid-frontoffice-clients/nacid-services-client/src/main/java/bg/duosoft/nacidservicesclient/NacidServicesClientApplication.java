package bg.duosoft.nacidservicesclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NacidServicesClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacidServicesClientApplication.class, args);
    }

}
