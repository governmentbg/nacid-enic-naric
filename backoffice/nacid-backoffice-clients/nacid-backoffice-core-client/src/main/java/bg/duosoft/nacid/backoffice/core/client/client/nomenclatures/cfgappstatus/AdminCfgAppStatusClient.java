package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminAppStatusConfigClient", url = "${feign.backoffice-core.base-url}/v1/cfg-application-status", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgAppStatusClient extends BaseCfgAppStatusClient {
}
