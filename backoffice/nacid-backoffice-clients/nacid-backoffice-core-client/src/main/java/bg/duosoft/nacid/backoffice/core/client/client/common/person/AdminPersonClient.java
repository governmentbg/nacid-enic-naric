package bg.duosoft.nacid.backoffice.core.client.client.common.person;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminPersonClient", url = "${feign.backoffice-core.base-url}/v1/persons", configuration = ClientTokenFeignConfig.class)
public interface AdminPersonClient extends PersonBaseClient {
}
