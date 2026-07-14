package bg.duosoft.nacid.backoffice.core.client.client.common.personalnacidid;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PersonalNacidIdClient", url = "${feign.backoffice-core.base-url}/v1/personal-nacid-id", configuration = SecContextFeignConfig.class)
public interface PersonalNacidIdClient extends PersonalNacidIdBaseClient {

}
