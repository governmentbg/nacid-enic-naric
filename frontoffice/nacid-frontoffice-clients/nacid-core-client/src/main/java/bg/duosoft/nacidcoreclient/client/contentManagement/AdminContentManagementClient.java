package bg.duosoft.nacidcoreclient.client.contentManagement;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "AdminContentManagementClient", url = "${feign.core-api.base-url}/v1/content-management", configuration = ClientTokenFeignConfig.class)
public interface AdminContentManagementClient extends BaseContentManagementClient {


}
