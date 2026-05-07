package bg.duosoft.nacid.backoffice.core.client.client.common.applicantattacheddocs;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ApplicationAttachedDocsClient", url = "${feign.backoffice-core.base-url}/v1/application-attached-docs", configuration = SecContextFeignConfig.class)
public interface ApplicationAttachedDocsClient extends ApplicationAttachedDocsBaseClient {

}
