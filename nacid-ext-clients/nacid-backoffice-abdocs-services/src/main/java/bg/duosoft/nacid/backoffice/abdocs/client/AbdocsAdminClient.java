package bg.duosoft.nacid.backoffice.abdocs.client;

import bg.duosoft.nacid.backoffice.abdocs.config.security.AdminAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"stage", "production"})
@FeignClient(name = "AbdocsAdminClient", url = "${feign.abdocs-api.base-url}", configuration = AdminAuthFeignConfig.class)
public interface AbdocsAdminClient extends BaseAbdocsClient {

}
