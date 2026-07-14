package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AppStatusConfigClient", url = "${feign.backoffice-core.base-url}/v1/cfg-application-status", configuration = {SecContextFeignConfig.class})
public interface CfgAppStatusClient extends BaseCfgAppStatusClient {

}
