package bg.duosoft.nacid.backoffice.core.client.client.common.abdocs;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminAbdocsFileTransferClient", url = "${feign.backoffice-core.base-url}/v1/application-abdocs-file-transfer", configuration = ClientTokenFeignConfig.class)
public interface AdminAbdocsFileTransferClient extends AbdocsFileTransferBaseClient {

}
