package bg.duosoft.nacid.backoffice.regprof.client.config;

import bg.duosoft.nacidshareddata.util.security.TokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 16:58
 */
@Slf4j
public class ClientTokenFeignClientInterceptor implements RequestInterceptor {

    @Autowired(required = false)
    private TokenManager tokenManager;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(tokenManager != null){
            requestTemplate.header("Authorization", "Bearer " + tokenManager.getAccessToken());
        } else {
            log.error("TokenManager should not be null if you want to call admin clients and pass Authorization header");
        }
    }
}
