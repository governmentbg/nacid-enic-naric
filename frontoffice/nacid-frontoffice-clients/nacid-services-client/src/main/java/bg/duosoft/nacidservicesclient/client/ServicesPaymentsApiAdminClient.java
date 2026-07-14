package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidservicesclient.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.07.2023
 * Time: 10:47
 */
@FeignClient(name = "ServicesPaymentsApiAdminClient", url = "${feign.nacid-services-be.base-url}/v1/payments-api", configuration = ClientTokenFeignConfig.class)
public interface ServicesPaymentsApiAdminClient extends ServicesPaymentsApiBaseClient {
}
