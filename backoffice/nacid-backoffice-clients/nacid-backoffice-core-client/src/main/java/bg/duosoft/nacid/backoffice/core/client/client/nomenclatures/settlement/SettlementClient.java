package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.settlement;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SettlementClient", url = "${feign.backoffice-core.base-url}/v1/settlements", configuration = {SecContextFeignConfig.class})
public interface SettlementClient extends BaseSettlementClient {

}
