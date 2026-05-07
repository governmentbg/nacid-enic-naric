package bg.duosoft.nacidcoreclient.client.settlement;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSettlementClient", url = "${feign.core-api.base-url}/v1/settlements", configuration = ClientTokenFeignConfig.class)
public interface AdminSettlementClient extends BaseSettlementClient {
}
