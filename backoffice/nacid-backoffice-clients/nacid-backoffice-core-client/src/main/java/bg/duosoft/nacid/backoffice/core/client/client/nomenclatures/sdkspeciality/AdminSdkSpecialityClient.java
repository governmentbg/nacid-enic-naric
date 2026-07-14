package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.sdkspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSdkSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/sdk-specialities", configuration = ClientTokenFeignConfig.class)
public interface AdminSdkSpecialityClient extends BaseSdkSpecialityClient {
}
