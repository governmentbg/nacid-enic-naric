package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradingscale;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "GradingScaleClient", url = "${feign.backoffice-core.base-url}/v1/grading-scales", configuration = {SecContextFeignConfig.class})
public interface GradingScaleClient extends BaseGradingScaleClient {

}
