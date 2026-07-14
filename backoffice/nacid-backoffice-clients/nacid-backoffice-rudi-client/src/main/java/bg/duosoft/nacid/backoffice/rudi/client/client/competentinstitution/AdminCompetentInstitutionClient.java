package bg.duosoft.nacid.backoffice.rudi.client.client.competentinstitution;

import bg.duosoft.nacid.backoffice.rudi.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCompetentInstitutionClient", url = "${feign.backoffice-rudi.base-url}/v1/competent-institution", configuration = ClientTokenFeignConfig.class)
public interface AdminCompetentInstitutionClient extends CompetentInstitutionBaseClient {
}
