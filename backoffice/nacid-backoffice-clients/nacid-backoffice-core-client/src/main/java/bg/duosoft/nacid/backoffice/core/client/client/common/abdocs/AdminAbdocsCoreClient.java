package bg.duosoft.nacid.backoffice.core.client.client.common.abdocs;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 14:25
 */
@FeignClient(name = "AdminAbdocsCoreClient", url = "${feign.backoffice-core.base-url}/v1/abdocs-docs", configuration = ClientTokenFeignConfig.class)
public interface AdminAbdocsCoreClient extends AbdocsCoreBaseClient {
}
