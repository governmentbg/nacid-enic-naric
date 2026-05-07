package bg.duosoft.nacidservicesbe.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.06.2022
 * Time: 11:39
 */
@Configuration
@ComponentScan(basePackages = {
        "bg.duosoft.nacidcoredata.mapper",
        "bg.duosoft.nacidshareddata.property",
        "bg.duosoft.nacidshareddata.service",
        "bg.duosoft.nacidshared.web",
        "bg.duosoft.nacidcoreclient.config",
        "bg.duosoft.nacidbackofficepublicservicesclient.config",
        "bg.duosoft.nacid.payments.client.config",
})
public class NacidServicesConfig {
}
