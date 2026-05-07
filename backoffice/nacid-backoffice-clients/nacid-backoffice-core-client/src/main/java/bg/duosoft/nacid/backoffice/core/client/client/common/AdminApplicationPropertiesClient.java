package bg.duosoft.nacid.backoffice.core.client.client.common;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminApplicationPropertiesClient", url = "${feign.backoffice-core.base-url}/v1/application-properties", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationPropertiesClient extends ApplicationPropertiesBaseClient {
}
