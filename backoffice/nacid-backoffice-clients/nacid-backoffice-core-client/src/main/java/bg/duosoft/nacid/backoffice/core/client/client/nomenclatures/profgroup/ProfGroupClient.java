package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.profgroup;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProfGroupClient", url = "${feign.backoffice-core.base-url}/v1/prof-group", configuration = {SecContextFeignConfig.class})
public interface ProfGroupClient extends BaseProfGroupClient {

}
