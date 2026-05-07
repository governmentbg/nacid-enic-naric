package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.nationalqualificationframework;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminNationalQualificationFrameworkClient", url = "${feign.backoffice-core.base-url}/v1/national-qualifications-framework", configuration = ClientTokenFeignConfig.class)
public interface AdminNationalQualificationFrameworkClient extends BaseNationalQualificationFrameworkClient {
}
