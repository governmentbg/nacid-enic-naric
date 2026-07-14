package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.bolognacycle;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "AdminBolognaCycleClient", url = "${feign.backoffice-core.base-url}/v1/bologna-cycle", configuration = ClientTokenFeignConfig.class)
public interface AdminBolognaCycleClient extends BaseBolognaCycleClient {
}
