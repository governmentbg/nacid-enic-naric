package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "ExternalNomenclaturesMapClient", url = "${feign.backoffice-core.base-url}/v1/external-nomenclatures-map", configuration = SecContextFeignConfig.class)
public interface ExternalNomenclaturesMapClient extends BaseExternalNomenclaturesMapClient {
}
