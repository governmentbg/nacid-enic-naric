package bg.duosoft.nacidservicesbe.config;

import bg.duosoft.nacidshared.web.config.security.ResourceServerDefaultSecurityConfig;
import bg.duosoft.nacidshareddata.config.security.TokenManagerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.06.2022
 * Time: 14:03
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {

    @Configuration
    @Import({ResourceServerDefaultSecurityConfig.class, TokenManagerConfig.class})
    public static class SecurityConfig {}
}
