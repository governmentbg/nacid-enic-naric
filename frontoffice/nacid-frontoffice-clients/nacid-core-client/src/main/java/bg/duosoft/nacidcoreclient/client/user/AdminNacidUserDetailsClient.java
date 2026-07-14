package bg.duosoft.nacidcoreclient.client.user;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 10:48
 */
@FeignClient(name = "AdminNacidUserDetailsClient", url = "${feign.core-api.base-url}/v1/nacid-user-details", configuration = ClientTokenFeignConfig.class)
public interface AdminNacidUserDetailsClient extends BaseNacidUserDetailsClient {

    @GetMapping("/for-username/{username}")
    NacidUserDetailsDTO getNacidUserDetailsForUsername(@PathVariable String username);

    @GetMapping("/for-email/{email}")
    NacidUserDetailsDTO getNacidUserDetailsForEmail(@PathVariable String email);
}
