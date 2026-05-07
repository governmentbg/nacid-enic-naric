package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.regprofarticledirective;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "RegprofArticleDirectiveClient", url = "${feign.backoffice-core.base-url}/v1/article-directives", configuration = {SecContextFeignConfig.class})
public interface RegprofArticleDirectiveClient extends BaseRegprofArticleDirectiveClient {

}
