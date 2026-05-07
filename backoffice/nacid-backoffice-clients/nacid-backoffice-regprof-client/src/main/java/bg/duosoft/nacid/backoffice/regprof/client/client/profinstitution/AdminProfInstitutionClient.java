package bg.duosoft.nacid.backoffice.regprof.client.client.profinstitution;

import bg.duosoft.nacid.backoffice.regprof.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(name = "AdminProfInstitutionClient", url = "${feign.backoffice-regprof.base-url}/v1/prof-institutions", configuration = ClientTokenFeignConfig.class)
public interface AdminProfInstitutionClient extends ProfInstitutionBaseClient {
}
