package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgeduleveltoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CfgEduLevelToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-edu-level-to-app-type", configuration = {SecContextFeignConfig.class})
public interface CfgEduLevelToAppTypeClient extends BaseCfgEduLevelToAppTypeClient {

}
