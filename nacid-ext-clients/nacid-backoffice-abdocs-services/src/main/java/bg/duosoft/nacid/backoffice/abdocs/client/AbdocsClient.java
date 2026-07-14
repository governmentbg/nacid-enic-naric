package bg.duosoft.nacid.backoffice.abdocs.client;

import bg.duosoft.nacid.backoffice.abdocs.config.security.LoggedUserAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"stage", "production"})
@FeignClient(name = "AbdocsClient", url = "${feign.abdocs-api.base-url}", configuration = LoggedUserAuthFeignConfig.class)
public interface AbdocsClient extends BaseAbdocsClient {

}
