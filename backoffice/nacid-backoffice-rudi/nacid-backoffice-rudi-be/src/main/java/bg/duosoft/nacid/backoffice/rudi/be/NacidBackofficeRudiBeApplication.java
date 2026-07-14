package bg.duosoft.nacid.backoffice.rudi.be;

import bg.duosoft.nacidshared.web.util.appinfo.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacidBackofficeRudiBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacidBackofficeRudiBeApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
