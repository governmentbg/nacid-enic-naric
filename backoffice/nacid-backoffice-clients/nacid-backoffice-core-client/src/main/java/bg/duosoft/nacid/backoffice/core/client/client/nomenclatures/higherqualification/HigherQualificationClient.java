package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.higherqualification;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "HigherQualificationClient", url = "${feign.backoffice-core.base-url}/v1/higher-qualifications", configuration = {SecContextFeignConfig.class})
public interface HigherQualificationClient extends BaseHigherQualificationClient {

}
