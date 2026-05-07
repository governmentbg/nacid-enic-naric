package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfggraduationwaytoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CfgGraduationWayToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-graduation-way-to-app-type", configuration = {SecContextFeignConfig.class})
public interface CfgGraduationWayToAppTypeClient extends BaseCfgGraduationWayToAppTypeClient {

}
