package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgeduleveltoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCfgEduLevelToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-edu-level-to-app-type", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgEduLevelToAppTypeClient extends BaseCfgEduLevelToAppTypeClient {
}
