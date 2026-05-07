package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ReferenceDataClient", url = "${feign.backoffice-core.base-url}/v1/reference-data", configuration = {SecContextFeignConfig.class})
public interface ReferenceDataClient extends BaseReferenceDataClient {

}
