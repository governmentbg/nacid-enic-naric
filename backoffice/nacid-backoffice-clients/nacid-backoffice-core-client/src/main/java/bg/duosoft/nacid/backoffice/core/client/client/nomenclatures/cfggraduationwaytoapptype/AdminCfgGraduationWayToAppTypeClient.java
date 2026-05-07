package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfggraduationwaytoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCfgGraduationWayToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-graduation-way-to-app-type", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgGraduationWayToAppTypeClient extends BaseCfgGraduationWayToAppTypeClient {
}
