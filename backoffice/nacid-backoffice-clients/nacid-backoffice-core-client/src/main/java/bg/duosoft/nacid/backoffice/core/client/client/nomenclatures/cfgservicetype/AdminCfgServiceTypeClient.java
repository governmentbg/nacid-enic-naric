package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgservicetype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCfgServiceTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-service-types", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgServiceTypeClient extends BaseCfgServiceTypeClient {
}
