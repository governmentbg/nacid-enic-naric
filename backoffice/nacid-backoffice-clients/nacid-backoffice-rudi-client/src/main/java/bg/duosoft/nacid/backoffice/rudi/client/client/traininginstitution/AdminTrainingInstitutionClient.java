package bg.duosoft.nacid.backoffice.rudi.client.client.traininginstitution;

import bg.duosoft.nacid.backoffice.rudi.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminTrainingInstitutionClient", url = "${feign.backoffice-rudi.base-url}/v1/training-institution", configuration = ClientTokenFeignConfig.class)
public interface AdminTrainingInstitutionClient extends TrainingInstitutionBaseClient {
}
