package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradingscaledetails;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminGradingScaleDetailsClient", url = "${feign.backoffice-core.base-url}/v1/grading-scale-details", configuration = ClientTokenFeignConfig.class)
public interface AdminGradingScaleDetailsClient extends BaseGradingScaleDetailsClient {
}
