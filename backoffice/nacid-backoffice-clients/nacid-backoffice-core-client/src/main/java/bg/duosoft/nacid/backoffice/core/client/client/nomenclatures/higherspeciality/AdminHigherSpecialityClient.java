package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.higherspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminHigherSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/higher-specialities", configuration = ClientTokenFeignConfig.class)
public interface AdminHigherSpecialityClient extends BaseHigherSpecialityClient {
}
