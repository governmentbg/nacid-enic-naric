package bg.duosoft.nacidcoreclient.client.settlement;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SettlementClient", url = "${feign.core-api.base-url}/v1/settlements", configuration = SecContextFeignConfig.class)
public interface SettlementClient extends BaseSettlementClient {

}
