package bg.duosoft.nacid.backoffice.core.client.client.common;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "ApplicationPropertiesClient", url = "${feign.backoffice-core.base-url}/v1/application-properties", configuration = SecContextFeignConfig.class)
public interface ApplicationPropertiesClient extends ApplicationPropertiesBaseClient {
}
