package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgservicetype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CfgServiceTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-service-types", configuration = {SecContextFeignConfig.class})
public interface CfgServiceTypeClient extends BaseCfgServiceTypeClient {

}
