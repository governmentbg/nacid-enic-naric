package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.bolognacycle;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "BolognaCycleClient", url = "${feign.backoffice-core.base-url}/v1/bologna-cycle", configuration = {SecContextFeignConfig.class})
public interface BolognaCycleClient extends BaseBolognaCycleClient {

}
