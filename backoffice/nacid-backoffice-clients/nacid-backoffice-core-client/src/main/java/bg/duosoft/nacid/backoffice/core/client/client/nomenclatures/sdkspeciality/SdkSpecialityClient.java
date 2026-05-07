package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.sdkspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SdkSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/sdk-specialities", configuration = {SecContextFeignConfig.class})
public interface SdkSpecialityClient extends BaseSdkSpecialityClient {

}
