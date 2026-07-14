package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.07.2023
 * Time: 13:47
 */
@FeignClient(name = "ServicesPaymentsApiClient", url = "${feign.nacid-services-be.base-url}/v1/payments-api", configuration = SecContextFeignConfig.class)
public interface ServicesPaymentsApiClient extends ServicesPaymentsApiBaseClient {


}
