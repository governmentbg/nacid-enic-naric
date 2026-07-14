package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.language;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "LanguageClient", url = "${feign.backoffice-core.base-url}/v1/languages", configuration = {SecContextFeignConfig.class})
public interface LanguageClient extends BaseLanguageClient {

}
