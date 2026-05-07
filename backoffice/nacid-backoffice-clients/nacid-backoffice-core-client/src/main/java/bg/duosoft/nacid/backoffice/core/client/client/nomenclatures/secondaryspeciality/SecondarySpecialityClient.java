package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SecondarySpecialityClient", url = "${feign.backoffice-core.base-url}/v1/secondary-specialities", configuration = {SecContextFeignConfig.class})
public interface SecondarySpecialityClient extends BaseSecondarySpecialityClient {

}
