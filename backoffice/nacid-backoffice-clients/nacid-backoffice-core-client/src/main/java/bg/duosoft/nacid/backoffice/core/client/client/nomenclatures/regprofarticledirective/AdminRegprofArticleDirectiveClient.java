package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.regprofarticledirective;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminRegprofArticleDirectiveClient", url = "${feign.backoffice-core.base-url}/v1/article-directives", configuration = ClientTokenFeignConfig.class)
public interface AdminRegprofArticleDirectiveClient extends BaseRegprofArticleDirectiveClient {
}
