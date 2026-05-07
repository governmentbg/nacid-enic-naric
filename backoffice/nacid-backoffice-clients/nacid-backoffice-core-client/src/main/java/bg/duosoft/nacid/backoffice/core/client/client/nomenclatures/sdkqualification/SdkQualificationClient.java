package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.sdkqualification;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SdkQualificationClient", url = "${feign.backoffice-core.base-url}/v1/sdk-qualifications", configuration = {SecContextFeignConfig.class})
public interface SdkQualificationClient extends BaseSdkQualificationClient {

}
