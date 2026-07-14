package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSecondarySpecialityClient", url = "${feign.backoffice-core.base-url}/v1/secondary-specialities", configuration = ClientTokenFeignConfig.class)
public interface AdminSecondarySpecialityClient extends BaseSecondarySpecialityClient {
}
