package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.legalreason;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "LegalReasonClient", url = "${feign.backoffice-core.base-url}/v1/legal-reason", configuration = {SecContextFeignConfig.class})
public interface LegalReasonClient extends BaseLegalReasonClient {

}
