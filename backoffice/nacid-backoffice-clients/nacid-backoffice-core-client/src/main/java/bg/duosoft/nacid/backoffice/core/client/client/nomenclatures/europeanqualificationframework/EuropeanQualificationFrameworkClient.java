package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.europeanqualificationframework;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "EuropeanQualificationFrameworkClient", url = "${feign.backoffice-core.base-url}/v1/european-qualifications-framework", configuration = {SecContextFeignConfig.class})
public interface EuropeanQualificationFrameworkClient extends BaseEuropeanQualificationFrameworkClient {

}
