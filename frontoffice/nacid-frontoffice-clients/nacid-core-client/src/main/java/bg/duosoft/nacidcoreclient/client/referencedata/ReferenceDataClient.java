package bg.duosoft.nacidcoreclient.client.referencedata;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ReferenceDataClient", url = "${feign.core-api.base-url}/v1/reference-data", configuration = SecContextFeignConfig.class)
public interface ReferenceDataClient extends BaseReferenceDataClient {

}
