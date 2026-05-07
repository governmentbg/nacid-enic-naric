package bg.duosoft.nacid.backoffice.rudi.be.config;

import bg.duosoft.nacidshared.web.config.security.ResourceServerDefaultSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {

    @Configuration
    @Import(ResourceServerDefaultSecurityConfig.class)
    public static class SecurityConfig {}
}
