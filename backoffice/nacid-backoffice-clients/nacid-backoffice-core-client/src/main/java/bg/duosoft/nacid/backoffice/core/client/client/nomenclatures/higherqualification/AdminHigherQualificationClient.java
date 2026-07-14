package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.higherqualification;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminHigherQualificationClient", url = "${feign.backoffice-core.base-url}/v1/higher-qualifications", configuration = ClientTokenFeignConfig.class)
public interface AdminHigherQualificationClient extends BaseHigherQualificationClient {
}
