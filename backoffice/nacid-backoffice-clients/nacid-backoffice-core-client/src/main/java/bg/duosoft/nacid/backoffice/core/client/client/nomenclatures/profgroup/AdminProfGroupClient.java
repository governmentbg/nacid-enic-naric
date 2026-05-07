package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.profgroup;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminProfGroupClient", url = "${feign.backoffice-core.base-url}/v1/prof-group", configuration = ClientTokenFeignConfig.class)
public interface AdminProfGroupClient extends BaseProfGroupClient {
}
