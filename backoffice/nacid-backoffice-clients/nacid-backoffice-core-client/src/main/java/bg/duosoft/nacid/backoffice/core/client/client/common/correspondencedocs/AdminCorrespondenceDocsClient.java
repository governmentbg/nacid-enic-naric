package bg.duosoft.nacid.backoffice.core.client.client.common.correspondencedocs;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCorrespondenceDocsClient", url = "${feign.backoffice-core.base-url}/v1/correspondence-docs", configuration = ClientTokenFeignConfig.class)
public interface AdminCorrespondenceDocsClient extends CorrespondenceDocsBaseClient {
}
