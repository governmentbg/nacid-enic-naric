package bg.duosoft.nacidcoreclient.client.documentReceiveMethod;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "DocumentReceiveMethodClient", url = "${feign.core-api.base-url}/v1/document-receive-methods", configuration = SecContextFeignConfig.class)
public interface DocumentReceiveMethodClient extends BaseDocumentReceiveMethodClient {
}
