package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminReferenceDataClient", url = "${feign.backoffice-core.base-url}/v1/reference-data", configuration = ClientTokenFeignConfig.class)
public interface AdminReferenceDataClient extends BaseReferenceDataClient {
}
