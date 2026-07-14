package bg.duosoft.nacidservicesbe;

import bg.duosoft.nacidshared.web.util.appinfo.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacidServicesBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacidServicesBeApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
