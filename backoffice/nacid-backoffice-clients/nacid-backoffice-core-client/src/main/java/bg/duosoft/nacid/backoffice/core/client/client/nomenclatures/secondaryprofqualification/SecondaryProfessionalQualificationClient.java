package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryprofqualification;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SecondaryProfessionalQualificationClient", url = "${feign.backoffice-core.base-url}/v1/secondary-professional-qualifications", configuration = {SecContextFeignConfig.class})
public interface SecondaryProfessionalQualificationClient extends BaseSecondaryProfessionalQualificationClient {

}
