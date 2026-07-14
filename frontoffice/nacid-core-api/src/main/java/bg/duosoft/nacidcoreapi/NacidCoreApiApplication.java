package bg.duosoft.nacidcoreapi;

import bg.duosoft.nacidshared.web.util.appinfo.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NacidCoreApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacidCoreApiApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
