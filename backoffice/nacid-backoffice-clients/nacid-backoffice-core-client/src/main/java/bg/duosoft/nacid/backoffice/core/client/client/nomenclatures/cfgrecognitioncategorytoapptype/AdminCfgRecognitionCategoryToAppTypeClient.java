package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgrecognitioncategorytoapptype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCfgRecognitionCategoryToAppTypeClient", url = "${feign.backoffice-core.base-url}/v1/cfg-recognition-category-to-app-type", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgRecognitionCategoryToAppTypeClient extends BaseCfgRecognitionCategoryToAppTypeClient {
}
