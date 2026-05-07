package bg.duosoft.nacidcoreclient.client.documentReceiveMethod;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminDocumentReceiveMethodClient", url = "${feign.core-api.base-url}/v1/document-receive-methods", configuration = ClientTokenFeignConfig.class)
public interface AdminDocumentReceiveMethodClient extends BaseDocumentReceiveMethodClient {
}
