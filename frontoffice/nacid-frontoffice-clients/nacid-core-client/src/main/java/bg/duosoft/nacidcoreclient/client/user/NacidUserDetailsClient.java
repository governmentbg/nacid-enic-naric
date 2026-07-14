package bg.duosoft.nacidcoreclient.client.user;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 10:47
 */
@FeignClient(name = "NacidUserDetailsClient", url = "${feign.core-api.base-url}/v1/nacid-user-details", configuration = SecContextFeignConfig.class)
public interface NacidUserDetailsClient extends BaseNacidUserDetailsClient {
}
