package bg.duosoft.nacidcoreclient.client.contentManagement;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ContentManagementClient", url = "${feign.core-api.base-url}/v1/content-management", configuration = SecContextFeignConfig.class)
public interface ContentManagementClient extends BaseContentManagementClient {

}

