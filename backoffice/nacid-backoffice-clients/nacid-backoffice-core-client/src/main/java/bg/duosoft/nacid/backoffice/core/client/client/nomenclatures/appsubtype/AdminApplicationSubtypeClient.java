package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.appsubtype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminApplicationSubtypeClient", url = "${feign.backoffice-core.base-url}/v1/application-subtypes", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationSubtypeClient extends ApplicationSubtypeBaseClient {
}
