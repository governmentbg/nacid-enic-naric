package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradingscale;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminGradingScaleClient", url = "${feign.backoffice-core.base-url}/v1/grading-scales", configuration = ClientTokenFeignConfig.class)
public interface AdminGradingScaleClient extends BaseGradingScaleClient {
}
