package bg.duosoft.nacidshared.web.config;

import bg.duosoft.nacidshared.web.property.CorsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CorsOriginsConfig {

    private final CorsProperties corsProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                List<String> allowedOrigins = corsProperties.getAllowedOrigins();
                if (CollectionUtils.isEmpty(allowedOrigins)) {
                    log.warn("[CORS] Allowed origins list is empty !");
                    return;
                }

                registry
                        .addMapping("/**")
                        .allowedMethods(corsProperties.getAllowedMethods().toArray(String[]::new))
                        .allowedOrigins(allowedOrigins.toArray(String[]::new));
            }
        };
    }
}
