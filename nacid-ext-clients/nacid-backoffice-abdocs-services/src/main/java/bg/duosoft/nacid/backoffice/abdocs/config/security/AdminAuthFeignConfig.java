package bg.duosoft.nacid.backoffice.abdocs.config.security;

import bg.duosoft.nacid.backoffice.abdocs.config.security.decoder.RetreiveMessageErrorDecoder;
import bg.duosoft.nacid.backoffice.abdocs.config.security.interceptop.AdminAuthInterceptor;
import bg.duosoft.nacid.backoffice.abdocs.config.security.retryer.FeignRequestRetryer;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;


public class AdminAuthFeignConfig {

    @Bean
    public AdminAuthInterceptor requestInterceptor() {
        return new AdminAuthInterceptor();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new FeignRequestRetryer();
    }

}
