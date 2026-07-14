package bg.duosoft.nacid.backoffice.core.client.client.common.applicantattacheddocs;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminApplicationAttachedDocsClient", url = "${feign.backoffice-core.base-url}/v1/application-attached-docs", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationAttachedDocsClient extends ApplicationAttachedDocsBaseClient {

}
