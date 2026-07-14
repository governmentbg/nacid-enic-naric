package bg.duosoft.nacidbackofficepublicservicesclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NacidBackofficePublicServicesClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacidBackofficePublicServicesClientApplication.class, args);
    }

}
