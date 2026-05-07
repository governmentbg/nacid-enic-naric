package bg.duosoft.nacid.backoffice.secondary.client.config;

import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:57
 */
public class ClientTokenFeignConfig {

    @Bean
    public ClientTokenFeignClientInterceptor clientTokenFeignClientInterceptor() {
        return new ClientTokenFeignClientInterceptor();
    }
}
