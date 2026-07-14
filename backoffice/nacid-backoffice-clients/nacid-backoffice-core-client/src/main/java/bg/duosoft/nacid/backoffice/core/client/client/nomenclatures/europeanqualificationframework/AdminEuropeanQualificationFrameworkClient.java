package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.europeanqualificationframework;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminEuropeanQualificationFrameworkClient", url = "${feign.backoffice-core.base-url}/v1/european-qualifications-framework", configuration = ClientTokenFeignConfig.class)
public interface AdminEuropeanQualificationFrameworkClient extends BaseEuropeanQualificationFrameworkClient {
}
