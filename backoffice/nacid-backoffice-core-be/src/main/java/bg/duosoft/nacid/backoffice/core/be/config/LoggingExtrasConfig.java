package bg.duosoft.nacid.backoffice.core.be.config;

import bg.duosoft.logging.db.annotation.EnableLoggingDbExtras;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

/**
 * User: ggeorgiev
 * Date: 19.10.2022
 * Time: 11:54
 */
@Configuration
@EnableLoggingDbExtras
public class LoggingExtrasConfig {
    @Bean
    public Supplier<String> loggedUserGetter() {
        return () -> SecurityUtils.getUsername();
    }
}
