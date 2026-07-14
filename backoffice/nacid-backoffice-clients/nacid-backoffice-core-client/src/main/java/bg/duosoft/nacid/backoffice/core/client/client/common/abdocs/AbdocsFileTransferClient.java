package bg.duosoft.nacid.backoffice.core.client.client.common.abdocs;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AbdocsFileTransferClient", url = "${feign.backoffice-core.base-url}/v1/application-abdocs-file-transfer", configuration = SecContextFeignConfig.class)
public interface AbdocsFileTransferClient extends AbdocsFileTransferBaseClient {

}
