package bg.duosoft.nacid.backoffice.core.be;

import bg.duosoft.nacidshared.web.util.appinfo.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacidBackofficeCoreBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacidBackofficeCoreBeApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
