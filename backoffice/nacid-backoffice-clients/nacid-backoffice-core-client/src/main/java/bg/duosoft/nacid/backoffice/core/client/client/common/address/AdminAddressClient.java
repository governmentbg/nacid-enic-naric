package bg.duosoft.nacid.backoffice.core.client.client.common.address;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminAddressClient", url = "${feign.backoffice-core.base-url}/v1/addresses", configuration = ClientTokenFeignConfig.class)
public interface AdminAddressClient extends AddressBaseClient {
}
