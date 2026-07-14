package bg.duosoft.nacid.backoffice.rudi.client.client.traininginstitution;

import bg.duosoft.nacid.backoffice.rudi.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "TrainingInstitutionClient", url = "${feign.backoffice-rudi.base-url}/v1/training-institution", configuration = SecContextFeignConfig.class)
public interface TrainingInstitutionClient extends TrainingInstitutionBaseClient {
}
