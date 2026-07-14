package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.language;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminLanguageClient", url = "${feign.backoffice-core.base-url}/v1/languages", configuration = ClientTokenFeignConfig.class)
public interface AdminLanguageClient extends BaseLanguageClient {
}
