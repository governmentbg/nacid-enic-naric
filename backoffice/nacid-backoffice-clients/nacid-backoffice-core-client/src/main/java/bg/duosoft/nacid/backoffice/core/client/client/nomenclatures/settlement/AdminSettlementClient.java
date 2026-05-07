package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.settlement;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSettlementClient", url = "${feign.backoffice-core.base-url}/v1/settlements", configuration = ClientTokenFeignConfig.class)
public interface AdminSettlementClient extends BaseSettlementClient {
}
