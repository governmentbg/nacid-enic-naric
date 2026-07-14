package bg.duosoft.nacid.backoffice.core.client.client.common.person;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PersonClient", url = "${feign.backoffice-core.base-url}/v1/persons", configuration = SecContextFeignConfig.class)
public interface PersonClient extends PersonBaseClient {
}
