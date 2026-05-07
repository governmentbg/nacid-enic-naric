package bg.duosoft.nacid.backoffice.core.client.config;

import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 23.06.2022
 * Time: 15:16
 */
@Slf4j
public class SecContextFeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(SecurityUtils.isUserAuthenticated()) {
            requestTemplate.header("Authorization", "Bearer " + SecurityUtils.getAccessToken());
        }
    }
}
