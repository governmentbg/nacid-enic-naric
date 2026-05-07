package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.sdkqualification;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSdkQualificationClient", url = "${feign.backoffice-core.base-url}/v1/sdk-qualifications", configuration = ClientTokenFeignConfig.class)
public interface AdminSdkQualificationClient extends BaseSdkQualificationClient {
}
