package bg.duosoft.nacid.backoffice.abdocs.config.security;

import bg.duosoft.nacid.backoffice.abdocs.config.security.decoder.RetreiveMessageErrorDecoder;
import bg.duosoft.nacid.backoffice.abdocs.config.security.interceptop.LoggedUserAuthInterceptor;
import bg.duosoft.nacid.backoffice.abdocs.config.security.retryer.FeignRequestRetryer;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;


public class LoggedUserAuthFeignConfig {

    @Bean
    public LoggedUserAuthInterceptor requestInterceptor() {
        return new LoggedUserAuthInterceptor();
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
