package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryprofqualification;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSecondaryProfessionalQualificationClient", url = "${feign.backoffice-core.base-url}/v1/secondary-professional-qualifications", configuration = ClientTokenFeignConfig.class)
public interface AdminSecondaryProfessionalQualificationClient extends BaseSecondaryProfessionalQualificationClient {
}
