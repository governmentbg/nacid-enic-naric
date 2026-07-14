package bg.duosoft.nacid.backoffice.core.client.client.common.personalnacidid;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminPersonalNacidIdClient", url = "${feign.backoffice-core.base-url}/v1/personal-nacid-id", configuration = ClientTokenFeignConfig.class)
public interface AdminPersonalNacidIdClient extends PersonalNacidIdBaseClient {
}
