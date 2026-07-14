package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidservicesclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 11:50
 */
@FeignClient(name = "ServicesApplicationCorrespondenceAdminClient", url = "${feign.nacid-services-be.base-url}/v1/app-correspondence", configuration = ClientTokenFeignConfig.class)
public interface ServicesApplicationCorrespondenceAdminClient extends ServicesApplicationCorrespondenceBaseClient {
}
