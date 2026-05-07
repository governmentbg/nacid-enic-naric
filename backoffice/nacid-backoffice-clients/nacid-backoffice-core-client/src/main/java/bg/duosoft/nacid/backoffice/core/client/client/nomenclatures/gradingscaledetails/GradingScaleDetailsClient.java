package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradingscaledetails;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "GradingScaleDetailsClient", url = "${feign.backoffice-core.base-url}/v1/grading-scale-details", configuration = {SecContextFeignConfig.class})
public interface GradingScaleDetailsClient extends BaseGradingScaleDetailsClient {

}
