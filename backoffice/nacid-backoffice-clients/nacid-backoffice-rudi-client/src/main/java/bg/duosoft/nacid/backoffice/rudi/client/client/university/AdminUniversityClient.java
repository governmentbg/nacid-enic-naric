package bg.duosoft.nacid.backoffice.rudi.client.client.university;

import bg.duosoft.nacid.backoffice.rudi.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminUniversityClient", url = "${feign.backoffice-rudi.base-url}/v1/universities", configuration = ClientTokenFeignConfig.class)
public interface AdminUniversityClient extends UniversityBaseClient {
}
