package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.civilidtype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCivilIdTypeClient", url = "${feign.backoffice-core.base-url}/v1/civil-id-types", configuration = ClientTokenFeignConfig.class)
public interface AdminCivilIdTypeClient extends CivilIdTypeBaseClient {
}
