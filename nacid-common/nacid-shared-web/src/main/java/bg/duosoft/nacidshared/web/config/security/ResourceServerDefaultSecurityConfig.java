package bg.duosoft.nacidshared.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.06.2022
 * Time: 14:03
 */
@Configuration
@EnableWebSecurity
public class ResourceServerDefaultSecurityConfig {

    @Order(10)
    @Configuration
    public static class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable().cors()
                    .and().antMatcher("/**")
                    .anonymous()
                    .and().oauth2ResourceServer().jwt();
        }

        @Bean
        public JwtAuthenticationConverter jwtAuthenticationConverter() {
            CustomJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
            JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
            return jwtAuthenticationConverter;
        }
    }

    @Order(9)
    @Configuration
    public static class BasicWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Value("${swagger-ui.basic.auth.user:swagger}")
        private String basicAuthUser;

        @Value("${swagger-ui.basic.auth.pass:$2a$10$ht58i3nglYeatzl79KL8fODj5VRhuvevlAPFYMLWOzYwm/w/ZT3Di}") //swagger
        private String basicAuthPass;

        @Value("${swagger-ui.basic.auth.role:swagger}")
        private String basicAuthRole;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable()
                    .antMatcher("/swagger-ui/**")
                    .httpBasic().and().authorizeRequests().antMatchers("/swagger-ui/**").hasRole(basicAuthRole);
        }

        @Bean
        public UserDetailsService userDetailsService() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.builder().username(basicAuthUser).password(basicAuthPass).roles(basicAuthRole).build());
            return manager;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
