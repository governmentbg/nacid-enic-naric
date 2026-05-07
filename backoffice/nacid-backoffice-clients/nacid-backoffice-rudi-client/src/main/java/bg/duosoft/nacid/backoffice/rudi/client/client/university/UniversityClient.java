package bg.duosoft.nacid.backoffice.rudi.client.client.university;

import bg.duosoft.nacid.backoffice.rudi.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "UniversityClient", url = "${feign.backoffice-rudi.base-url}/v1/universities", configuration = SecContextFeignConfig.class)
public interface UniversityClient extends UniversityBaseClient {
}
