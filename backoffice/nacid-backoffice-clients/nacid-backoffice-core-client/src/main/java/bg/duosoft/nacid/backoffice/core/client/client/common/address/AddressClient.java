package bg.duosoft.nacid.backoffice.core.client.client.common.address;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AddressClient", url = "${feign.backoffice-core.base-url}/v1/addresses", configuration = SecContextFeignConfig.class)
public interface AddressClient extends AddressBaseClient {


}
