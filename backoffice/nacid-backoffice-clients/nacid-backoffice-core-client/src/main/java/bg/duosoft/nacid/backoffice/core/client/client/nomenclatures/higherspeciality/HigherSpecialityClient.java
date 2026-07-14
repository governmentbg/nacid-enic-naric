package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.higherspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "HigherSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/higher-specialities", configuration = {SecContextFeignConfig.class})
public interface HigherSpecialityClient extends BaseHigherSpecialityClient {

}
