package bg.duosoft.nacid.backoffice.core.client.client.common.correspondencedocs;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CorrespondenceDocsClient", url = "${feign.backoffice-core.base-url}/v1/correspondence-docs", configuration = SecContextFeignConfig.class)
public interface CorrespondenceDocsClient extends CorrespondenceDocsBaseClient {


}
