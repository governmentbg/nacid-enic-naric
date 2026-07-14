package bg.duosoft.nacid.backoffice.core.client.config;

import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:56
 */
public class SecContextFeignConfig {

    @Bean
    public SecContextFeignClientInterceptor requestInterceptor() {
        return new SecContextFeignClientInterceptor();
    }
}
