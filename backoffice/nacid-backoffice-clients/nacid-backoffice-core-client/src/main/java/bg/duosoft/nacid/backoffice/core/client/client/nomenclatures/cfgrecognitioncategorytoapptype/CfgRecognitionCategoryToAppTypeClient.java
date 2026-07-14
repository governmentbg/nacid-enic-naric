package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgrecognitioncategorytoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CfgRecognitionCategoryToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-recognition-category-to-app-type", configuration = {SecContextFeignConfig.class})
public interface CfgRecognitionCategoryToAppTypeClient extends BaseCfgRecognitionCategoryToAppTypeClient {

}
