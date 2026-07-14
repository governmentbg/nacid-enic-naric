package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.legalreason;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminLegalReasonClient", url = "${feign.backoffice-core.base-url}/v1/legal-reason", configuration = ClientTokenFeignConfig.class)
public interface AdminLegalReasonClient extends BaseLegalReasonClient {
}
