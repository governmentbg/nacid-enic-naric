package bg.duosoft.nacidcoreclient.client.referencedata;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminReferenceDataClient", url = "${feign.core-api.base-url}/v1/reference-data", configuration = ClientTokenFeignConfig.class)
public interface AdminReferenceDataClient extends BaseReferenceDataClient {
}
